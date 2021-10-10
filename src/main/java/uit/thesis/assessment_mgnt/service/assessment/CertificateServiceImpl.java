package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.certificate.UpdatedCertificate;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;
import uit.thesis.assessment_mgnt.repository.assessment.CertificateRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, Long> implements CertificateService {
    private CertificateRepository certificateRepository;
    private ModelMapper modelMapper;


    @Override
    public Certificate generateCertificateCode() {
        Certificate certificate = new Certificate();
        certificate.setCode(RandomStringUtils.randomAlphanumeric(10));
        return this.certificateRepository.save(certificate);
    }

    @Override
    public Certificate updateCertiface(UpdatedCertificate dto, String code) throws Exception {
        Certificate certificate = certificateRepository.findByCode(code);
        if(certificate == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Certificate"));
        this.modelMapper.map(dto, certificate);
        return certificateRepository.save(certificate);
    }
}
