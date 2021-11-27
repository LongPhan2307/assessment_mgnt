package uit.thesis.assessment_mgnt.dto.workflow.invoice;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateInvoiceDto {
    @NotBlank
    private String name;

    @NotBlank
    private String surveyCode;

    @NotBlank
    private String customerCode;
}
