package uit.thesis.assessment_mgnt.service.workflow;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreateWorkflowDto;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;

public interface WorkflowService extends GenericService<Workflow, Long> {
    Workflow addNewWorkflow(CreateWorkflowDto dto);

    Workflow generateBasicWorkflow();
}
