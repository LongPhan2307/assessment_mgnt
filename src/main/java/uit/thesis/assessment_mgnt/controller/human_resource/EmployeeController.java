package uit.thesis.assessment_mgnt.controller.human_resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.human_resource.CreateEmployeeDto;
import uit.thesis.assessment_mgnt.model.human_resource.Employee;
import uit.thesis.assessment_mgnt.service.human_resource.EmployeeService;
import uit.thesis.assessment_mgnt.utils.EmployeeDegree;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.EmployeeDomain;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(Domain.API)
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping(EmployeeDomain.EMPLOYEE_DOMAIN)
    public ResponseEntity<Object> getAllEmployee(){
        List<Employee> list = employeeService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @GetMapping( Domain.API_ACCOUNTANT + EmployeeDomain.EMPLOYEE_DOMAIN + "/degree")
    public ResponseEntity<Object> getAllEmployeeDegree(){
        List<EmployeeDegree> list = Arrays.asList(EmployeeDegree.values());
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(Domain.API_ACCOUNTANT + EmployeeDomain.EMPLOYEE_DOMAIN)
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody CreateEmployeeDto dto,
                                                 BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Employee employee = employeeService.save(dto);
            return ResponseObject.getResponse(employee, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping(Domain.API_ACCOUNTANT + EmployeeDomain.EMPLOYEE_DOMAIN + "/{employee_id}")
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody CreateEmployeeDto dto,
                                                 BindingResult errors,
                                                 @PathVariable("employee_id") String employeeId){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        if(employeeId == null || employeeId.equals(""))
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("EmployeeID"), HttpStatus.OK);
        try {
            Employee employee = employeeService.update(dto, employeeId);
            return ResponseObject.getResponse(employee, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.OK);
        }
    }
}
