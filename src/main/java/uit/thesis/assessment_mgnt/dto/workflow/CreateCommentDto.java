package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCommentDto {
    @NotBlank
    private String content;

    @NotBlank
    private String surveyCode;
}
