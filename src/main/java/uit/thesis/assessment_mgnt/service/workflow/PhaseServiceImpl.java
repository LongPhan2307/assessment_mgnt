package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class PhaseServiceImpl extends GenericServiceImpl<Phase, Long> implements PhaseService {
    private PhaseRepository phaseRepository;
    private WorkflowRepository workflowRepository;
    private ModelMapper modelMapper;

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
}
