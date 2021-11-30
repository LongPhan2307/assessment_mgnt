package uit.thesis.assessment_mgnt.controller.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;
import uit.thesis.assessment_mgnt.model.workflow.Expense;
import uit.thesis.assessment_mgnt.service.workflow.ConfirmationService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.ConfirmationDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Domain.API)
public class ConfirmationController {
    private ConfirmationService confirmationService;

    @GetMapping(ConfirmationDomain.CONFIRMATION_DOMAIN)
    public ResponseEntity<Object> findAll(){
        List<Confirmation> list = confirmationService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

//    @GetMapping(ConfirmationDomain.CONFIRMATION_DOMAIN + "/search")
//    public ResponseEntity<Object> findByCommentId(@RequestParam("comment") long commentId){
//        List<Confirmation> list = confirmationService.findByCommentId(commentId);
//        if(list.isEmpty())
//            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
//        return ResponseObject.getResponse(list, HttpStatus.OK);
//    }
//
//    @PutMapping(ConfirmationDomain.CONFIRMATION_DOMAIN + "/change-status")
//    public ResponseEntity<Object> changeStatusConfirm(@RequestParam("comment") long commentId
//                                                      ){
//        try {
//            Comment comment = confirmationService.changeStatusConfirmation(commentId);
//            return ResponseObject.getResponse(comment, HttpStatus.OK);
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
