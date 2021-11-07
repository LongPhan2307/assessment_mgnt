package uit.thesis.assessment_mgnt.dto.assessment.survey;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateSurveyDto {
    @NotBlank
    private String name;

    @NotBlank
    private String scene;

    @NotBlank
    private String contactPhone;

    @NotBlank
    private String assessmentCategoryCode;

    @NotBlank
    private String customerCode;

    @Min(value = 3)
    private long duration;

}
