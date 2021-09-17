package uit.thesis.assessment_mgnt.dto.human_resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class CreatePayslipDto {
    @NotBlank
    private String salaryDescription;

    @Min(value = 0)
    private BigDecimal bonus;

    @NotBlank
    private String bonusDescription;

    @Min(value = 0)
    private BigDecimal penaltyPay;

    @NotBlank
    private String penaltyPayDescription;

    @NotBlank
    private String employeeId;

}
