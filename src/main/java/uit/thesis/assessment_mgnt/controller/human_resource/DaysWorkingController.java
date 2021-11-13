package uit.thesis.assessment_mgnt.controller.human_resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.human_resource.CreateDaysWorkingDto;
import uit.thesis.assessment_mgnt.model.human_resource.DaysWorking;
import uit.thesis.assessment_mgnt.service.human_resource.DaysWorkingService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.DaysWorkingDomain;
import uit.thesis.assessment_mgnt.utils.domain.Domain;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(Domain.API)
public class DaysWorkingController {
    private DaysWorkingService daysWorkingService;

    @GetMapping(Domain.API_ACCOUNTANT + DaysWorkingDomain.DAYS_WORKING_DOMAIN)
    public ResponseEntity<Object> getAllDaysWorking(){
        List<DaysWorking> list = daysWorkingService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @GetMapping(Domain.API_ACCOUNTANT + DaysWorkingDomain.DAYS_WORKING_DOMAIN + "/all")
    public ResponseEntity<Object> getAll(){
        List<DaysWorking> list = daysWorkingService.getAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }


    @PostMapping(Domain.API_ACCOUNTANT + DaysWorkingDomain.DAYS_WORKING_DOMAIN)
    public ResponseEntity<Object> addDaysWorking(@Valid @RequestBody CreateDaysWorkingDto dto,
                                                 BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            DaysWorking daysWorking = daysWorkingService.save(dto);
            return ResponseObject.getResponse(daysWorking, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse((e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(Domain.API_ACCOUNTANT + DaysWorkingDomain.DAYS_WORKING_DOMAIN + "/{employee_id}")
    public ResponseEntity<Object> getAllDaysWorkingFromEmployeeId(@PathVariable("employee_id") String employeeId){
        if(employeeId == null || employeeId.equals(""))
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("EmployeeID"), HttpStatus.BAD_REQUEST);
        List<DaysWorking> list = daysWorkingService.getAllDaysWorkingByEmployeeId(employeeId);
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PutMapping(Domain.API_ACCOUNTANT + DaysWorkingDomain.DAYS_WORKING_DOMAIN + "/{code}")
    public ResponseEntity<Object> update(@Valid @RequestBody CreateDaysWorkingDto dto,
                                                 BindingResult errors,
                                                 @PathVariable("code") String code){
        if(code == null || code.equals(""))
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("Code DayWorking"), HttpStatus.BAD_REQUEST);
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            DaysWorking daysWorking = daysWorkingService.update(dto, code);
            return ResponseObject.getResponse(daysWorking, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse((e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
}
