package uit.thesis.assessment_mgnt.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "{user.username.not-blank}")
    private String username;

    @NotBlank(message = "{user.password.not-blank}")
    private String password;
}
