package uit.thesis.assessment_mgnt.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.controller.assessment.JwtDto;
import uit.thesis.assessment_mgnt.security.dto.LoginDto;
import uit.thesis.assessment_mgnt.security.jwt.JwtUtils;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto dto,
                                        BindingResult errors){
        Authentication authentication = null;
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            // authenticate
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

            // set authentication into SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            JwtDto jwt = new JwtDto();
            jwt.setJwt(jwtToken);
            return ResponseObject.getResponse(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return ResponseObject.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }
}
