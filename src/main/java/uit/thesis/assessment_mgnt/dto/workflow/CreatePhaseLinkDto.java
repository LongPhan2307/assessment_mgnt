package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreatePhaseLinkDto {

    @NotBlank
    private String transition;

    @NotBlank
    private String linkBy;

    @NotBlank
    private String linkTo;

    @NotBlank
    private String workflowName;
}
