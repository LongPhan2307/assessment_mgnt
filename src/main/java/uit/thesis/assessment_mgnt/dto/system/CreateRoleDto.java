package uit.thesis.assessment_mgnt.dto.system;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateRoleDto {
    @Enumerated(EnumType.STRING)
    @NotBlank
    private RoleName name;

    private String description;
}
