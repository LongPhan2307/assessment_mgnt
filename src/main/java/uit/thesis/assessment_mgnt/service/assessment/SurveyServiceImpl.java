package uit.thesis.assessment_mgnt.service.assessment;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateSurveyDto;
import uit.thesis.assessment_mgnt.dto.assessment.survey.ResponseSurvey;
import uit.thesis.assessment_mgnt.dto.assessment.survey.SurveyWithUsers;
import uit.thesis.assessment_mgnt.dto.assessment.survey.UpdateSurveyDto;
import uit.thesis.assessment_mgnt.dto.workflow.CreateCommentDto;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.assessment.AssessmentCategoryRepository;
import uit.thesis.assessment_mgnt.repository.assessment.CustomerRepository;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.CommentRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.service.workflow.CommentService;
import uit.thesis.assessment_mgnt.service.workflow.ConfirmationService;
import uit.thesis.assessment_mgnt.utils.MapDtoToModel;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.Const;
import uit.thesis.assessment_mgnt.utils.survey.Status;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class SurveyServiceImpl extends GenericServiceImpl<Survey, Long> implements SurveyService {
    private SurveyRepository surveyRepository;
    private ModelMapper modelMapper;
    private CertificateService certificateService;
    private AssessmentCategoryRepository assessmentCategoryRepository;
    private PhaseRepository phaseRepository;
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private CommentService commentService;
    private CommentRepository commentRepository;
    private ConfirmationService confirmationService;

    @Override
    public Survey addNewSurvey(CreateSurveyDto dto) throws NotFoundException {
        Survey survey = new Survey();
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User accountant = userRepository.findByUsername(currentUsername);
        User director = userRepository.findByUsername("director");
        User manager = userRepository.findByUsername("manager");
        Customer customer = customerRepository.findByCode(dto.getCustomerCode());
        if(customer == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Customer "));
        if(accountant == null)
            throw new NotFoundException("Anonymous is using service ");
        AssessmentCategory assessmentCategory = assessmentCategoryRepository.findByCode(dto.getAssessmentCategoryCode());
        if(assessmentCategory == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Assessment Category Name"));
        Phase phase = phaseRepository.findByName(Const.PHASE_START);
        if(phase == null){
            throw new NotFoundException("Phase Start");
        }
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime dueDate = today.plusDays(dto.getDuration());
        survey = modelMapper.map(dto, Survey.class);
        survey.setCode(RandomStringUtils.randomAlphanumeric(10));
        survey.setAssessmentCategory(assessmentCategory);
        survey.setCustomer(customer);
        survey.setStatus(Status.PENDING);
        survey.setDirector(director);
        survey.setManager(manager);
        survey.setDueDate(dueDate);
        survey.setPhase(phase);
        survey.setAccountant(accountant);
        Survey createdSurvey = surveyRepository.save(survey);
        Certificate certificate = certificateService.generateCertificateCode(createdSurvey);
        survey.setCertificate(certificate);
        return createdSurvey;
    }

    @Override
    public List<Status> getAllStatus() {
        List<Status> list = Arrays.asList(Status.values());
        return list;
    }

    @Override
    public Survey updateEsimatePrice(String surveyCode, BigDecimal esimatePrice) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(surveyCode);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        survey.setEstimatePrice(esimatePrice);
        String content = currentUser.getUsername() + " has added Estimate Price = " + esimatePrice;
        Comment comment = new Comment(content, Const.ADDING_ESTIMATE_PRICE, currentUser, survey);
        commentRepository.save(comment);
        return surveyRepository.save(survey);
    }

    @Override
    public Survey findByCode(String code) {
        return surveyRepository.findByCode(code);
    }

    @Override
    public List<Survey> getSurveysByDirector(String directorName) {
        List<Survey> list = surveyRepository.findByDirectorName(directorName);
        return list;
    }


    @Override
    public List<ResponseSurvey> getAllSurveyCode() {
        return surveyRepository.getAll();
    }

    @Override
    public List<SurveyWithUsers> getAll() {
        List<Survey> list = surveyRepository.findAll();
        List<SurveyWithUsers> surveys = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            SurveyWithUsers survey = mapSurveyToSurveyWithUsers(list.get(i));
            survey.setAccountant(list.get(i).getAccountant());
            survey.setManager(list.get(i).getManager());
            survey.setDirector(list.get(i).getDirector());
            survey.setInspectors(list.get(i).getInspectors());
            surveys.add(survey);
        }
        return surveys;
    }

    @Override
    public Survey changeUserOfSurvey(String username, String surveyCode) throws Exception {
        Survey survey = surveyRepository.findByCode(surveyCode);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        if(currentUser.getUsername().equals(survey.getAccountant().getUsername())){

            User userChanged = userRepository.findByUsername(username);
            if(userChanged == null)
                throw new NotFoundException(ResponseMessage.UN_KNOWN("User "));

            survey.setAccountant(userChanged);
            commentService.generateComment(userChanged, survey, currentUser);
            return surveyRepository.save(survey);
        } else if(currentUser.getUsername().equals(survey.getDirector().getUsername())){

            User userChanged = userRepository.findByUsername(username);
            if(userChanged == null)
                throw new NotFoundException(ResponseMessage.UN_KNOWN("User "));

            survey.setDirector(userChanged);
            commentService.generateComment(userChanged, survey, currentUser);
            return surveyRepository.save(survey);
        } else if(currentUser.getUsername().equals(survey.getManager().getUsername())){

            User userChanged = userRepository.findByUsername(username);
            if(userChanged == null)
                throw new NotFoundException(ResponseMessage.UN_KNOWN("User "));

            survey.setManager(userChanged);
            commentService.generateComment(userChanged, survey, currentUser);
            return surveyRepository.save(survey);
        }

        throw new Exception("You can not change User of this Survey ");
    }

    @Override
    public Survey assignInspectors(String[] usernames, String surveyCode) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(surveyCode);
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        Comment comment = new Comment("Please go ahead !",
                Const.ASSIGN_INSPECTORS,
                currentUser,
                survey);
        commentRepository.save(comment);
        confirmationService.createConfirmation(usernames, comment);
        return surveyRepository.save(survey);
    }

    private SurveyWithUsers mapSurveyToSurveyWithUsers(Survey survey){
        SurveyWithUsers surveyWithUsers = new SurveyWithUsers();
        surveyWithUsers.setId(survey.getId());
        surveyWithUsers.setCode(survey.getCode());
        surveyWithUsers.setCreateAt(survey.getCreateAt());
        surveyWithUsers.setCreatedBy(survey.getCreatedBy());
        surveyWithUsers.setCreatedBy(survey.getCreatedBy());
        surveyWithUsers.setLastModifiedBy(survey.getLastModifiedBy());
        surveyWithUsers.setManager(survey.getManager());
        surveyWithUsers.setInspectors(survey.getInspectors());
        surveyWithUsers.setDirector(survey.getDirector());
        surveyWithUsers.setAccountant(survey.getAccountant());
        surveyWithUsers.setDueDate(survey.getDueDate());
        surveyWithUsers.setName(survey.getName());
        surveyWithUsers.setUpdateAt(survey.getUpdateAt());
        surveyWithUsers.setScene(survey.getScene());
        surveyWithUsers.setContactPhone(survey.getContactPhone());
        return surveyWithUsers;
    }


    @Override
    public Survey assigneeApproval(String[] arrUsername, String surveyCode) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(surveyCode);
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
//        CreateCommentDto commentDto = new CreateCommentDto(
//                "Please confirm if you can proceed next step !",
//                surveyCode
//        );
//        Comment comment = commentService.addComment(commentDto);
        Comment comment = new Comment("Please go ahead !",
                Const.ASSIGNEE_APPROVAL,
                currentUser,
                survey);
        commentRepository.save(comment);
        for(int i =0; i< arrUsername.length; i++){
            User user = userRepository.findByUsername(arrUsername[i]);
            if( user != null)
                survey.addInspector(user);
        }
        return surveyRepository.save(survey);
    }

    @Override
    public Survey updateSurvey(UpdateSurveyDto dto, String code) throws Exception {
        Survey survey = surveyRepository.findByCode(code);
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        if(code == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Survey Code"));
        this.modelMapper.map(dto, survey);
//        survey = this.surveyRepository.save(survey);
        survey.setCode(code);
        String content = currentUser.getUsername() + " has changed content of survey ";
        Comment comment = new Comment(content, Const.CHANGE_CONTENT, currentUser, survey);
        commentRepository.save(comment);
        return this.surveyRepository.save(survey);
    }
}
