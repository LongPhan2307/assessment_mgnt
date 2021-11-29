package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateInvoiceDto;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateSurchargeDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;
import uit.thesis.assessment_mgnt.repository.assessment.CustomerRepository;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.CommentRepository;
import uit.thesis.assessment_mgnt.repository.workflow.ExpenseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.InvoiceRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PaymentRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.Const;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, Long> implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private SurveyRepository surveyRepository;
    private CustomerRepository customerRepository;
    private ExpenseRepository expenseRepository;
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Override
    public Invoice createInvoice(CreateInvoiceDto dto) throws Exception {
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        Customer customer = customerRepository.findByCode(dto.getCustomerCode());
        BigDecimal price = new BigDecimal("0");
        BigDecimal expensePrice = new BigDecimal("0");
        BigDecimal recentDebt = new BigDecimal("0");
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        if(customer == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Customer "));
        if(survey.getEstimatePrice() == null)
            throw new Exception("Estimate Price is null. Please update !");
        Invoice invoice = new Invoice();
        invoice.setName(dto.getName());
        invoice.setCustomer(customer);
        invoice.setSurvey(survey);
        expensePrice = expenseRepository.ExpenseTotal(survey.getCode());
        recentDebt = paymentRepository.getRecentDebt(customer.getCode());
        price = price.add(survey.getEstimatePrice()).add(expensePrice).add(recentDebt);
        invoice.setPrice(price);
        String content = "Export invoice for " + customer.getCustNameVN();
        Comment comment = new Comment(content, Const.ADDING_INVOICE, currentUser, survey);
        commentRepository.save(comment);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice addNewSurcharge(CreateSurchargeDto dto) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        Customer customer = customerRepository.findByCode(dto.getCustomerCode());
        User currentUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(currentUser == null)
            throw new NotFoundException(ResponseMessage.ANONYMOUS_USER);
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        if(customer == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Customer "));
        Invoice invoice = new Invoice();
        invoice.setPrice(dto.getSurcharge());
        invoice.setName(dto.getName());
        invoice.setCustomer(customer);
        invoice.setSurvey(survey);
        String content = "Export surcharge for " + customer.getCustNameVN();
        Comment comment = new Comment(content, Const.ADDING_INVOICE, currentUser, survey);
        commentRepository.save(comment);
        return invoiceRepository.save(invoice);
    }
}
