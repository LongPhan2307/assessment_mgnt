package uit.thesis.assessment_mgnt.dto.assessment.document;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class CreateDocumentDto {
    @NotBlank
    private String name;

    private String description;

    private String surveyCode;
}
