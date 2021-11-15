package uit.thesis.assessment_mgnt.repository.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;

import java.util.List;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

    @Query("select c from Confirmation c where c.comment.id = ?1")
    List<Confirmation> findByCommentId(long commentId);
}
