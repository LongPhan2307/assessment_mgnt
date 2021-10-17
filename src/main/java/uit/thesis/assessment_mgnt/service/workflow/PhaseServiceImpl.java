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
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.Const;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PhaseServiceImpl extends GenericServiceImpl<Phase, Long> implements PhaseService {
    private PhaseRepository phaseRepository;
    private WorkflowRepository workflowRepository;
    private ModelMapper modelMapper;
    private SurveyRepository surveyRepository;

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
        Set<PhaseLink> links = source.getWorkflow().getPhaseLinks();
        Iterator<PhaseLink> iterator = links.iterator();
        while (iterator.hasNext()){
            PhaseLink newLink = iterator.next();
            if(newLink.getLinkBy().getName().equals(sourceName)){
                if(newLink.getLinkTo() == null)
                    throw new Exception("Phase Destination is not exists");
                Phase updatedPhase = newLink.getLinkTo();
                survey.setPhase(updatedPhase);

                return surveyRepository.save(survey);
            }
        }
        throw new Exception("Phase is not exists in Workflow");
    }
}
