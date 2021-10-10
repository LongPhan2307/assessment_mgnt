package uit.thesis.assessment_mgnt.service.workflow;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreateWorkflowDto;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.survey.Const;

@Service
@AllArgsConstructor
public class WorkflowServiceImpl extends GenericServiceImpl<Workflow, Long> implements WorkflowService{
    private WorkflowRepository workflowRepository;
    private PhaseRepository phaseRepository;
    private ModelMapper modelMapper;

    @Override
    public Workflow addNewWorkflow(CreateWorkflowDto dto) {
        Workflow workflow = new Workflow();
        workflow = modelMapper.map(dto, Workflow.class);

        return workflowRepository.save(workflow);
    }

    @Override
    public Workflow generateBasicWorkflow() {
        Workflow workflow = new Workflow();
        workflow.setName(Const.BASIC_WORKFLOW_NAME);
        workflow.setDescription(Const.BASIC_WORKFLOW_DESC);
        Phase newPhase = new Phase();
        newPhase.setName(Const.PHASE_START);
        newPhase.setNodeOrder(Const.INITAL_NODE_ORDER);
        newPhase.setWorkflow(workflow);
        workflowRepository.save(workflow);
        this.phaseRepository.save(newPhase);
        return workflow;
    }
}
