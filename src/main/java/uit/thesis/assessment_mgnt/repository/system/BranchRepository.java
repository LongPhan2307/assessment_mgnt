package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.system.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
