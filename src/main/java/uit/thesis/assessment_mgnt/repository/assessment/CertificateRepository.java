package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    public Certificate findByCode(String code);
}
