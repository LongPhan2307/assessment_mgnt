package uit.thesis.assessment_mgnt.controller.system;

import io.swagger.models.Response;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.system.CreateRoleDto;
import uit.thesis.assessment_mgnt.dto.system.CreateUserDto;
import uit.thesis.assessment_mgnt.dto.system.ResponseUserDto;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.service.system.UserService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.UserDomain;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Domain.API)
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping(UserDomain.USER_DOMAIN)
    public ResponseEntity<Object> getAllUser(){
        List<User> list = userService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @GetMapping(UserDomain.USER_DOMAIN + "/fetch")
    public ResponseEntity<Object> getUserInfoByUsername(@RequestParam("username") String username){
        try {
            ResponseUserDto user = userService.getUserByUsername(username);
            return ResponseObject.getResponse(user, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(Domain.API_ADMIN + UserDomain.USER_DOMAIN)
    public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserDto dto,
                                          BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        User user = null;
        try {
            user = userService.save(dto);
            return ResponseObject.getResponse(user, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(Domain.API_ADMIN + UserDomain.USER_DOMAIN + "/{username}/{role_name}")
    public ResponseEntity<Object> addRole(@PathVariable("role_name") String roleName,
                                          @PathVariable("username") String username){
        if(roleName == null || roleName.equals(""))
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("Role Name"), HttpStatus.BAD_REQUEST);
        if(username.equals("") || username == null)
            return ResponseObject.getResponse(ResponseMessage.NOT_BLANK("Username"), HttpStatus.BAD_REQUEST);
        try {
            User user = userService.addRole(roleName, username);
            return ResponseObject.getResponse(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
