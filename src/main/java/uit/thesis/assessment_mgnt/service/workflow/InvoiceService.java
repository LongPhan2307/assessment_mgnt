package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateInvoiceDto;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;

public interface InvoiceService extends GenericService<Invoice, Long> {
    Invoice createInvoice(CreateInvoiceDto dto) throws NotFoundException;
}
