package uit.thesis.assessment_mgnt.service.assessment;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.certificate.UpdatedCertificate;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

public interface CertificateService extends GenericService<Certificate, Long> {

    public Certificate generateCertificateCode(Survey survey);

    public Certificate updateCertiface(UpdatedCertificate dto, String code) throws Exception;


}
