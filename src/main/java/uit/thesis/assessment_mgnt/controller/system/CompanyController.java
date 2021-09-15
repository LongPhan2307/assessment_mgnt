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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<Object> getAllCompany(){
        List<Company> list = companyService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse("There is no data", HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCompany(@Valid @RequestBody CreateCompanyDto dto,
                                             BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Company company = companyService.save(dto);
        return ResponseObject.getResponse(company, HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Object> updateCompany(@Valid @RequestBody UpdateCompanyDto dto,
                                                BindingResult errors,
                                                @PathVariable("code") String code){
        if(code == null || code.equals("")){
            return ResponseObject.getResponse("Code is not blank", HttpStatus.BAD_REQUEST);
        }
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Company company = companyService.update(dto, code);
        if(company == null)
            return ResponseObject.getResponse("Code Company is not found", HttpStatus.BAD_REQUEST);
        return ResponseObject.getResponse(company, HttpStatus.OK);
    }


}
