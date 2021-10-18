package uit.thesis.assessment_mgnt.repository.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;

@Repository
public interface PhaseLinkRepository extends JpaRepository<PhaseLink, Long> {
//    PhaseLink findByLinkTo(Phase linkTo);
}
