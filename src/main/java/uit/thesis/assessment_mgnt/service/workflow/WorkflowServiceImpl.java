package uit.thesis.assessment_mgnt.service.workflow;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreateWorkflowDto;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;

@Service
@AllArgsConstructor
public class WorkflowServiceImpl extends GenericServiceImpl<Workflow, Long> implements WorkflowService{
    private WorkflowRepository workflowRepository;
    private ModelMapper modelMapper;

    @Override
    public Workflow addNewWorkflow(CreateWorkflowDto dto) {
        Workflow workflow = new Workflow();
        workflow = modelMapper.map(dto, Workflow.class);

        return workflowRepository.save(workflow);
    }
}
