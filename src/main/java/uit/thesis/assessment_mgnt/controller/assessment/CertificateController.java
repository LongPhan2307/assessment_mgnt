package uit.thesis.assessment_mgnt.controller.assessment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.certificate.UpdatedCertificate;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;
import uit.thesis.assessment_mgnt.service.assessment.CertificateService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.CertificateDomain;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Domain.API)
@AllArgsConstructor
public class CertificateController {
    private CertificateService certificateService;

    @GetMapping(CertificateDomain.CERTIFICATE)
    public ResponseEntity<Object> findAll(){
        List<Certificate> list = certificateService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PutMapping(CertificateDomain.CERTIFICATE)
    public ResponseEntity<Object> updateCertificate(@RequestParam( value = "desc") String code,
                                                    @Valid @RequestBody UpdatedCertificate dto,
                                                    BindingResult error){
        if(error.hasErrors())
            return ResponseObject.getResponse(error, HttpStatus.BAD_REQUEST);
        try {
            Certificate certificate = certificateService.updateCertiface(dto, code);
            return ResponseObject.getResponse(certificate, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
