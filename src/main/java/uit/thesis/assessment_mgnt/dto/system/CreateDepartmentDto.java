package uit.thesis.assessment_mgnt.dto.system;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepartmentDto {
    private String name;

    private String description;

    private String companyCode;
}
