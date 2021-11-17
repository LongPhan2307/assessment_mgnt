package uit.thesis.assessment_mgnt.dto.assessment.survey;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.utils.DateUtils;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateSurveyDto {
    private String name;

    private String scene;

    @JsonFormat(pattern = DateUtils.DATE)
    private LocalDateTime dueDate;

    private String contactPhone;

}
