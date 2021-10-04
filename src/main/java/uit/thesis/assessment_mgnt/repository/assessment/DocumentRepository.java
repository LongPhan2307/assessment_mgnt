package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.thesis.assessment_mgnt.model.assessment.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
