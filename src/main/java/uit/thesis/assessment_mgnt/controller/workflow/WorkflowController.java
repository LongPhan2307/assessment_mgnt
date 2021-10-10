package uit.thesis.assessment_mgnt.controller.workflow;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.workflow.CreateWorkflowDto;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.service.workflow.WorkflowService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.workflow.WorkflowDomain;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(WorkflowDomain.WORKFLOW)
public class WorkflowController {
    private WorkflowService workflowService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Workflow> list = workflowService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addWorkflow(@Valid @RequestBody CreateWorkflowDto dto,
                                              BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Workflow workflow = workflowService.addNewWorkflow(dto);
        return ResponseObject.getResponse(workflow, HttpStatus.CREATED);
    }

    @PostMapping("/generateBasicWorkflow")
    public ResponseEntity<Object> generateBasicWorkflow(){
        Workflow workflow = workflowService.generateBasicWorkflow();
        if(workflow != null)
            return ResponseObject.getResponse(workflow, HttpStatus.CREATED);
        return ResponseObject.getResponse("Generating is failure", HttpStatus.BAD_GATEWAY);
    }
}
