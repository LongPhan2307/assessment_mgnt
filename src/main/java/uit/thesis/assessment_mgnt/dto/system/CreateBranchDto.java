package uit.thesis.assessment_mgnt.dto.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateBranchDto {
    private String name;

    private String code;

    private String headQuaters;

    private String address;
}
