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
import uit.thesis.assessment_mgnt.dto.system.CreateRoleDto;
import uit.thesis.assessment_mgnt.dto.system.CreateUserDto;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.service.system.UserService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.UserDomain;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(UserDomain.USER_DOMAIN)
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> getAllUser(){
        List<User> list = userService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping("")
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
}
