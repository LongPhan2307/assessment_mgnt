package uit.thesis.assessment_mgnt.controller.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateInvoiceDto;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateSurchargeDto;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;
import uit.thesis.assessment_mgnt.service.workflow.InvoiceService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;

import java.util.List;

@RestController
@RequestMapping(Domain.API)
@AllArgsConstructor
public class InvoiceController {
    private InvoiceService invoiceService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Invoice> list = invoiceService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(Domain.API_ACCOUNTANT)
    public ResponseEntity<Object> createInvoice(@RequestBody CreateInvoiceDto dto,
                                                BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Invoice invoice = invoiceService.createInvoice(dto);
            return ResponseObject.getResponse(invoice, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(Domain.API_ACCOUNTANT + "/surcharge")
    public ResponseEntity<Object> addNewSurcharge(@RequestBody CreateSurchargeDto dto,
                                                BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Invoice invoice = invoiceService.addNewSurcharge(dto);
            return ResponseObject.getResponse(invoice, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
