package uit.thesis.assessment_mgnt.controller.workflow;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;
import uit.thesis.assessment_mgnt.model.workflow.Expense;
import uit.thesis.assessment_mgnt.service.workflow.ConfirmationService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.workflow.ConfirmationDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ConfirmationDomain.CONFIRMATION_DOMAIN)
public class ConfirmationController {
    private ConfirmationService confirmationService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Confirmation> list = confirmationService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }
}
