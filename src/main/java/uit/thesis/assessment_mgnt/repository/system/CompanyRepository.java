package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.system.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCode(String code);
}
