package uit.thesis.assessment_mgnt.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.system.Company;
import uit.thesis.assessment_mgnt.model.system.Department;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.service.assessment.AssessmentCategoryService;
import uit.thesis.assessment_mgnt.service.assessment.CustomerService;
import uit.thesis.assessment_mgnt.service.system.CompanyService;
import uit.thesis.assessment_mgnt.service.system.DepartmentService;
import uit.thesis.assessment_mgnt.service.system.RoleService;
import uit.thesis.assessment_mgnt.service.system.UserService;
import uit.thesis.assessment_mgnt.service.workflow.PhaseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/mockup")
public class MockupController {
    private CompanyService companyService;
    private DepartmentService departmentService;
    private RoleService roleService;
    private UserService userService;
    private AssessmentCategoryService assessmentCategoryService;
    private CustomerService customerService;
    private PhaseService phaseService;

    @PostMapping("/company")
    public ResponseEntity<Object> mockupCompany(){
        Company company = companyService.mockupCompanyData();
        return ResponseObject.getResponse(company, HttpStatus.OK);
    }

    @PostMapping("/department")
    public ResponseEntity<Object> mockupDepartment(){
        List<Department> list = null;
        try {
            list = departmentService.mockupData();
            return ResponseObject.getResponse(list, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/role")
    public ResponseEntity<Object> mockupRole(){
        List<Role> list = roleService.mockupData();
        return ResponseObject.getResponse(list, HttpStatus.CREATED);
    }

    @PostMapping("/user")
    public ResponseEntity<Object> mockupUser(){
        List<User> list = userService.mockupData();
        return ResponseObject.getResponse(list, HttpStatus.CREATED);
    }

    @PostMapping("/assessment-category")
    public ResponseEntity<Object> mockupAssessmentCategory(){
        List<AssessmentCategory> list = assessmentCategoryService.mockupData();
        return ResponseObject.getResponse(list, HttpStatus.CREATED);
    }

    @PostMapping("/customer")
    public ResponseEntity<Object> mockupCustomer(){
        List<Customer> list = customerService.mockupData();
        return ResponseObject.getResponse(list, HttpStatus.CREATED);
    }

    @PostMapping("/phase")
    public ResponseEntity<Object> mockupPhases(){
        List<Phase> list = phaseService.mockupData();
        return ResponseObject.getResponse(list, HttpStatus.CREATED);
    }

}
