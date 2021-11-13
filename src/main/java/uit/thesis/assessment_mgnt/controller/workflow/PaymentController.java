package uit.thesis.assessment_mgnt.controller.workflow;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.workflow.Payment;
import uit.thesis.assessment_mgnt.service.workflow.PaymentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.PaymentDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Domain.API)
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping(PaymentDomain.PAYMENT_DOMAIN)
    public ResponseEntity<Object> findAll(){
        List<Payment> list = paymentService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

}
