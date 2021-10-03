package uit.thesis.assessment_mgnt.dto.assessment.survey;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateSurveyDto {
    @NotBlank
    private String name;

    @NotBlank
    private String scene;

    @NotBlank
    private String contact;

    @NotBlank
    private String contactPhone;
}
