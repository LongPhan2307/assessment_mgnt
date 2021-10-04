package uit.thesis.assessment_mgnt.dto.assessment.certificate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatedCertificate {
    @NotBlank
    private String description;

    private MultipartFile file;
}
