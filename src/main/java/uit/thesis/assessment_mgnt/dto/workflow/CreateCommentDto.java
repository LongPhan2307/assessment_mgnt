package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDto {
    @NotBlank
    private String content;

    @NotBlank
    private String surveyCode;

    private String[] confirmedUser;

    public CreateCommentDto(String content, String surveyCode){
        this.content = content;
        this.surveyCode = surveyCode;
    }
}
