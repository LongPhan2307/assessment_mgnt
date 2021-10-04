package uit.thesis.assessment_mgnt.dto.assessment.svc_req_form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateSvcReqFrm {
    @NotBlank
    private String name;
}
