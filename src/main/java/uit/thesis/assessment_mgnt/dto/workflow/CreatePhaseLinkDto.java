package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.Getter;
import lombok.Setter;

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
}
