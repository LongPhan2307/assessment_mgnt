package uit.thesis.assessment_mgnt.controller.human_resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.human_resource.CreatePayslipDto;
import uit.thesis.assessment_mgnt.model.human_resource.Payslip;
import uit.thesis.assessment_mgnt.service.human_resource.PayslipService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.PayslipDomain;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Domain.API)
@AllArgsConstructor
public class PayslipController {
    private PayslipService payslipService;

    @GetMapping(PayslipDomain.PAYSLIP_DOMAIN)
    public ResponseEntity<Object> getAllPayslip(){
        List<Payslip> list = payslipService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(Domain.API_ACCOUNTANT + PayslipDomain.PAYSLIP_DOMAIN)
    public ResponseEntity<Object> createPayslip(@Valid @RequestBody CreatePayslipDto dto,
                                                BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Payslip payslip = payslipService.save(dto);
            return ResponseObject.getResponse(payslip, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(Domain.API_ACCOUNTANT + PayslipDomain.PAYSLIP_DOMAIN + "/{code}")
    public ResponseEntity<Object> updatePayslip(@Valid @RequestBody CreatePayslipDto dto,
                                                BindingResult errors,
                                                @PathVariable("code") String code){
        if(code == null || code.equals(""))
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("Code Payslip"), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Payslip payslip = payslipService.update(dto, code);
            return ResponseObject.getResponse(payslip, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
