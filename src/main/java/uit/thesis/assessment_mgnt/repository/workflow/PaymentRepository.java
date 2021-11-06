package uit.thesis.assessment_mgnt.repository.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.workflow.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
