package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.dto.workflow.UpdatePhaseDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.system.RoleRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.CommentRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseLinkRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.role.PhaseConst;
import uit.thesis.assessment_mgnt.utils.role.RoleName;
import uit.thesis.assessment_mgnt.utils.survey.Const;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class PhaseServiceImpl extends GenericServiceImpl<Phase, Long> implements PhaseService {
    private PhaseRepository phaseRepository;
    private WorkflowRepository workflowRepository;
    private ModelMapper modelMapper;
    private SurveyRepository surveyRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Override
    public Phase addPhase(CreatePhaseDto dto) throws NotFoundException {
        Phase phase = new Phase();
//        Workflow workflow = workflowRepository.findByName(dto.getWorkflowName());
//        if(workflow == null)
//            throw new NotFoundException(ResponseMessage.UN_KNOWN("Workflow "));
        phase = modelMapper.map(dto, Phase.class);
//        phase.setWorkflow(workflow);
        return phaseRepository.save(phase);
    }

    @Override
    public Phase generateStartPhase(String workflowName) throws NotFoundException {
        Phase newPhase = new Phase();
        Workflow workflow = workflowRepository.findByName(workflowName);
        if(workflow == null || workflowName.equals("") || workflowName == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Workflow "));
        newPhase.setName(Const.PHASE_START);
        newPhase.setNodeOrder(Const.INITAL_NODE_ORDER);
//        newPhase.setWorkflow(workflow);
        return phaseRepository.save(newPhase);
    }

    @Override
    public Survey submitPhase(UpdatePhaseDto dto) throws Exception {
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        Iterator iterator = currentUser.getRoles().iterator();
        while (iterator.hasNext()){
            Object ele = iterator.next();
        }
        Phase source = phaseRepository.findByName(dto.getPhaseName());
        Phase destination = phaseRepository.findByNodeOrder(source.getNodeOrder() + 1);
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Phase "));
        if(source == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Phase "));
        survey.setPhase(destination);
        Comment comment = new Comment();
        comment.setTitle(Const.SUBMITTED_PHASE);
        comment.setContent(dto.getContent());
        comment.setUser(currentUser);
        comment.setSurvey(survey);
        commentRepository.save(comment);
         return surveyRepository.save(survey);
    }

    @Override
    public Survey returnPhase(UpdatePhaseDto dto) throws Exception {
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        Phase destination = phaseRepository.findByName(dto.getPhaseName());
        Phase source = phaseRepository.findByNodeOrder(destination.getNodeOrder() - 1);
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        if(destination == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN(" Destination "));
        if( survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        if(source == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Source "));
        survey.setPhase(source);
        Comment comment = new Comment();
        comment.setTitle(Const.RETURNED_PHASE);
        comment.setContent(dto.getContent());
        comment.setUser(currentUser);
        comment.setSurvey(survey);
        commentRepository.save(comment);
        return surveyRepository.save(survey);
    }

    @Override
    public Survey declineAssignInspector(String surveyCode) throws NotFoundException {
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        Survey survey = surveyRepository.findByCode(surveyCode);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        Phase phase = phaseRepository.findByName(PhaseConst.ASSIGN_INSPECTORS.toString());
        if(phase == null)
            throw new NotFoundException("Phase Assign Inspectors is not exists");
        survey.setPhase(phase);
        survey.setInspectors(null);
        return surveyRepository.save(survey);
    }

    @Override
    public Phase findByNodeOrder(int nodeOrder) {
        return phaseRepository.findByNodeOrder(nodeOrder);
    }

    @Override
    public List<Phase> mockupData() {
        List<Phase> list = new LinkedList<>();
        List<Role> roles = roleRepository.findAll();
        list.add(new Phase("REGISTER", 1, roles.get(2)));
        list.add(new Phase("SURVEY CANCELLATION", 99, roles.get(0)));
        list.add(new Phase("ASSIGN INSPECTORS", 2, roles.get(3)));
        list.add(new Phase("IMPLEMENTS", 3, roles.get(3)));
        list.add(new Phase("CONCLUDE SURVEY", 4, roles.get(3)));
        list.add(new Phase("MANAGE PAYMENT", 5, roles.get(2)));
        list.add(new Phase("FINISH", 6, roles.get(0)));
        return phaseRepository.saveAll(list);
    }

    @Override
    public Phase updatePhaseRole(String phaseName, String roleName) throws NotFoundException {
        Phase phase = phaseRepository.findByName(phaseName);
        Role role = roleRepository.findByName(roleName);
        if(role == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Role "));
        if(phase == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Updated Phase "));
        phase.setRole(role);
        return phaseRepository.save(phase);
    }
}
