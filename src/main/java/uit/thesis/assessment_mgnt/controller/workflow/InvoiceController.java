package uit.thesis.assessment_mgnt.controller.workflow;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uit.thesis.assessment_mgnt.service.workflow.InvoiceService;
import uit.thesis.assessment_mgnt.utils.domain.Domain;

@RestController
@RequestMapping(Domain.API)
@AllArgsConstructor
public class InvoiceController {
    private InvoiceService invoiceService;
}
