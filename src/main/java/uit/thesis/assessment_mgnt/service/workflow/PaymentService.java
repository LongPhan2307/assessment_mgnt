package uit.thesis.assessment_mgnt.service.workflow;


import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.payment.CreatePaymentDto;
import uit.thesis.assessment_mgnt.model.workflow.Payment;

import java.math.BigDecimal;

public interface PaymentService extends GenericService<Payment, Long> {

    Payment addPayment(CreatePaymentDto dto) throws NotFoundException;
}
