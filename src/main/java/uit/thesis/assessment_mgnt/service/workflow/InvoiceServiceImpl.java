package uit.thesis.assessment_mgnt.service.workflow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;
import uit.thesis.assessment_mgnt.repository.workflow.InvoiceRepository;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, Long> implements InvoiceService {
    private InvoiceRepository invoiceRepository;


}
