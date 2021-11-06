package uit.thesis.assessment_mgnt.dto.assessment.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCustomerDto {
    @NotBlank
    private String code;

    @NotBlank
    private String custNameVN;

    private String custNameIT;

    private String address;

    private String phone;
}
