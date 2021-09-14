package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.thesis.assessment_mgnt.model.system.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
