package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateExpenseDto {
    @NotBlank
    private String title;

    @Min(value = 0)
    private BigDecimal cost;

    @NotBlank
    private String surveyCode;
}
