package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.payment.CreatePaymentDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Payment;
import uit.thesis.assessment_mgnt.repository.assessment.CustomerRepository;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.workflow.ExpenseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PaymentRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class PaymentServiceImpl extends GenericServiceImpl<Payment, Long> implements PaymentService {
    private ExpenseRepository expenseRepository;
    private SurveyRepository surveyRepository;
    private CustomerRepository customerRepository;
    private PaymentRepository paymentRepository;


    @Override
    public Payment addPayment(CreatePaymentDto dto) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        Payment payment = new Payment();
        Customer customer = customerRepository.findByCode(dto.getCustomerCode());
        BigDecimal estimatePrice = new BigDecimal("0");
        BigDecimal expenseTotal = new BigDecimal("0");
        BigDecimal totalPaid = new BigDecimal("0");
        BigDecimal debt = new BigDecimal("0");
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        if(customer == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Customer "));
        if(survey.getEstimatePrice() != null)
            estimatePrice = survey.getEstimatePrice();
        if(expenseRepository.ExpenseTotal(survey.getCode()) != null)
            expenseTotal = expenseRepository.ExpenseTotal(survey.getCode());
        if(paymentRepository.getTotalPaid(dto.getCustomerCode(), dto.getSurveyCode()) != null){
            totalPaid = paymentRepository.getTotalPaid(dto.getCustomerCode(), dto.getSurveyCode());
            debt = debt.add(totalPaid.subtract(estimatePrice).subtract(expenseTotal));
        } else {
            debt = debt.add(dto.getAmountPaid().subtract(estimatePrice).subtract(expenseTotal));
        }
        payment.setName(dto.getName());
        payment.setSurvey(survey);
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setDebt(debt);
        payment.setCustomer(customer);
        return paymentRepository.save(payment);
    }
}
