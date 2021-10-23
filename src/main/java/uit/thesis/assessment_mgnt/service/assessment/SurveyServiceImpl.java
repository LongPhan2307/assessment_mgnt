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
import uit.thesis.assessment_mgnt.dto.assessment.survey.UpdateSurveyDto;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.assessment.AssessmentCategoryRepository;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.Const;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    @Override
    public Survey addNewSurvey(CreateSurveyDto dto) throws NotFoundException {
        Survey survey = new Survey();
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User accountant = userRepository.findByUsername(currentUsername);
        if(accountant == null)
            throw new NotFoundException("Anonymous is using service ");
        AssessmentCategory assessmentCategory = assessmentCategoryRepository.findByName(dto.getAssessmentCategory());
        if(assessmentCategory == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Assessment Category Name"));
        Phase phase = phaseRepository.findByName(Const.PHASE_START);
        if(phase == null){
            throw new NotFoundException("Phase Start");
        }
        survey = modelMapper.map(dto, Survey.class);
        survey.setCode(RandomStringUtils.randomAlphanumeric(10));
        survey.setStatusForm(StatusForm.PENDING);
        survey.setAssessmentCategory(assessmentCategory);
        survey.setPhase(phase);
        survey.setAccountant(accountant);
        Survey createdSurvey = surveyRepository.save(survey);
        Certificate certificate = certificateService.generateCertificateCode(createdSurvey);
        survey.setCertificate(certificate);
        return createdSurvey;
    }

    @Override
    public List<StatusForm> getAllStatusForm() {
        List<StatusForm> list = Arrays.asList(StatusForm.values());
        return list;
    }

    @Override
    public Survey findByCode(String code) {
        return surveyRepository.findByCode(code);
    }


    @Override
    public List<ResponseSurvey> getAllSurveyCode() {
        return surveyRepository.getAll();
    }

    @Override
    public Survey assignInspectors(String[] arrUsername, String surveyCode) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(surveyCode);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
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
        if(code == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Survey Code"));
        this.modelMapper.map(dto, survey);
//        survey = this.surveyRepository.save(survey);
        survey.setCode(code);
        return this.surveyRepository.save(survey);
    }
}
