package uit.thesis.assessment_mgnt.controller.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.workflow.payment.CreatePaymentDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.workflow.Payment;
import uit.thesis.assessment_mgnt.service.workflow.PaymentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.PaymentDomain;

import java.math.BigDecimal;
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

    @PostMapping(Domain.API_ACCOUNTANT + PaymentDomain.PAYMENT_DOMAIN + "/add")
    public ResponseEntity<Object> addPayment(@RequestBody CreatePaymentDto dto,
                                             BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Payment payment = paymentService.addPayment(dto);
            return ResponseObject.getResponse(payment, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
