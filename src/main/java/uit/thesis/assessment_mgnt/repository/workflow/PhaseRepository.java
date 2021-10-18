package uit.thesis.assessment_mgnt.repository.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.workflow.Phase;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Long> {
    Phase findByName(String name);

    Phase findByNodeOrder(int nodeOrder);
}
