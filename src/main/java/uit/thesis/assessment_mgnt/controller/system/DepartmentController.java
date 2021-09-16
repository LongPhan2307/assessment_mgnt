package uit.thesis.assessment_mgnt.controller.system;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.system.CreateDepartmentDto;
import uit.thesis.assessment_mgnt.model.system.Department;
import uit.thesis.assessment_mgnt.service.system.DepartmentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.DepartmentDomain;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(DepartmentDomain.DEPARTMENT_DOMAIN)
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<Object> getAllDepartment(){
        List<Department> list = departmentService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addDepartment(@Valid @RequestBody CreateDepartmentDto dto,
                                                BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Department department = departmentService.save(dto);
            return ResponseObject.getResponse(department, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
