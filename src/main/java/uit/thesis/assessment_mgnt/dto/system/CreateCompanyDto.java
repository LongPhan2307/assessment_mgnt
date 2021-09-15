package uit.thesis.assessment_mgnt.dto.system;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCompanyDto {
    private String name;

    private String code;

    private String headquaterCode;

    private String address;
}
