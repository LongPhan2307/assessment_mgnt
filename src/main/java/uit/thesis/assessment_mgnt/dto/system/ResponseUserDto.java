package uit.thesis.assessment_mgnt.dto.system;

import lombok.Getter;
import lombok.Setter;

import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;


@Getter
@Setter
public class ResponseUserDto {
    private User userInfo;

    private String roleName;
}
