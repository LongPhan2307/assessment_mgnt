package uit.thesis.assessment_mgnt.dto.assessment.survey;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
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
    private String assessmentCategory;

    @NotBlank
    private String customerName;

    private LocalDateTime dueDate;

}
