package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseLinkRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.Const;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class PhaseServiceImpl extends GenericServiceImpl<Phase, Long> implements PhaseService {
    private PhaseRepository phaseRepository;
    private WorkflowRepository workflowRepository;
    private ModelMapper modelMapper;
    private SurveyRepository surveyRepository;
    private PhaseLinkRepository phaseLinkRepository;

    @Override
    public Phase addPhase(CreatePhaseDto dto) throws NotFoundException {
        Phase phase = new Phase();
        Workflow workflow = workflowRepository.findByName(dto.getWorkflowName());
        if(workflow == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Workflow "));
        phase = modelMapper.map(dto, Phase.class);
        phase.setWorkflow(workflow);
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
        newPhase.setWorkflow(workflow);
        return phaseRepository.save(newPhase);
    }

    @Override
    public Survey submitPhase(String sourceName, String surveyCode) throws Exception {
        Phase source = phaseRepository.findByName(sourceName);
        Survey survey = surveyRepository.findByCode(surveyCode);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Phase "));
        if(source == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Phase "));
        throw new Exception("Phase is not exists in Workflow");
    }

    @Override
    public Survey returnPhase(String destinationName, String surveyCode) throws Exception {
        Phase destination = phaseRepository.findByName(destinationName);
        Phase source = phaseRepository.findByNodeOrder(destination.getNodeOrder() - 1);
        Survey survey = surveyRepository.findByCode(surveyCode);
        if(destination == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN(" Destination "));
        if( survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        if(source == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Source "));
        survey.setPhase(source);
        return surveyRepository.save(survey);
//        Iterator<PhaseLink> iterator = links.iterator();
//        while (iterator.hasNext()){
//            PhaseLink newLink = iterator.next();
//            if(newLink.getLinkTo().getName().equals(destinationName)){
//                if(newLink.getLinkBy() == null)
//                    throw new Exception("Phase Source is not exists");
//                survey.setPhase(source);
//                return surveyRepository.save(survey);
//            }
//        }
//        throw new Exception("Phase is not exists in Workflow");
    }

    @Override
    public Phase findByNodeOrder(int nodeOrder) {
        return phaseRepository.findByNodeOrder(nodeOrder);
    }
}
