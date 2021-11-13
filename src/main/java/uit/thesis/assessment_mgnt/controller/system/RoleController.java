package uit.thesis.assessment_mgnt.controller.system;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.system.CreateRoleDto;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.service.system.RoleService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.RoleDomain;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Domain.API)
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;

    @GetMapping(RoleDomain.ROLE_DOMAIN)
    public ResponseEntity<Object> getAllRole(){
        List<Role> list = roleService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @GetMapping(RoleDomain.ROLE_DOMAIN + "/search")
    public ResponseEntity<Object> findUsersByRole(@RequestParam("role") String roleName){
        Role role = roleService.findUsersByRole(roleName);
        if(role == null)
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(role, HttpStatus.OK);
    }

    @PostMapping(Domain.API_ADMIN)
    public ResponseEntity<Object> addRole(@Valid @RequestBody CreateRoleDto dto,
                                          BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Role role = roleService.save(dto);
        return ResponseObject.getResponse(role, HttpStatus.CREATED);
    }


}
