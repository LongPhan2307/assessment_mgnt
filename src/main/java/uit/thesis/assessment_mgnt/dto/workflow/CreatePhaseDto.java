package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreatePhaseDto {

    @NotBlank
    private String name;

    private int nodeOrder;

//    @NotBlank
//    private String workflowName;
}
