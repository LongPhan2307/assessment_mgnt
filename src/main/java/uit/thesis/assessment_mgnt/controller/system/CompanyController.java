package uit.thesis.assessment_mgnt.controller.system;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.system.CreateCompanyDto;
import uit.thesis.assessment_mgnt.dto.system.UpdateCompanyDto;
import uit.thesis.assessment_mgnt.model.system.Company;
import uit.thesis.assessment_mgnt.service.system.CompanyService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.CompanyDomain;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Domain.API)
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;

    @GetMapping(CompanyDomain.COMPANY_DOMAIN)
    public ResponseEntity<Object> getAllCompany(){
        List<Company> list = companyService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }



    @PostMapping(Domain.API_ADMIN + CompanyDomain.COMPANY_DOMAIN)
    public ResponseEntity<Object> addCompany(@Valid @RequestBody CreateCompanyDto dto,
                                             BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Company company = companyService.save(dto);
        return ResponseObject.getResponse(company, HttpStatus.CREATED);
    }

    @PutMapping(Domain.API_ADMIN + CompanyDomain.COMPANY_DOMAIN)
    public ResponseEntity<Object> updateCompany(@Valid @RequestBody UpdateCompanyDto dto,
                                                BindingResult errors,
                                                @PathVariable("code") String code){
        if(code == null || code.equals("")){
            return ResponseObject.getResponse( ResponseMessage.NOT_BLANK("Code Company"), HttpStatus.BAD_REQUEST);
        }
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Company company = companyService.update(dto, code);
        if(company == null)
            return ResponseObject.getResponse(ResponseMessage.UN_KNOWN("Company"), HttpStatus.BAD_REQUEST);
        return ResponseObject.getResponse(company, HttpStatus.OK);
    }

    @DeleteMapping(Domain.API_ADMIN + CompanyDomain.COMPANY_DOMAIN)
    public ResponseEntity<Object> deleteCompany(
                                                @PathVariable("code") String code){
        if(code == null || code.equals("")){
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("Company Code"), HttpStatus.BAD_REQUEST);
        }

        boolean res = companyService.deleteByCode(code);
        if(!res)
            return ResponseObject.getResponse(ResponseMessage.UN_KNOWN("Company"), HttpStatus.BAD_REQUEST);
        return ResponseObject.getResponse(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

}
