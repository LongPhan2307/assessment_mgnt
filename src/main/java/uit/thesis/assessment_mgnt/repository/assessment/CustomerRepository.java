package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.assessment.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
