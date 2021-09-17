package uit.thesis.assessment_mgnt.repository.human_resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.human_resource.Payslip;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, Long> {
}
