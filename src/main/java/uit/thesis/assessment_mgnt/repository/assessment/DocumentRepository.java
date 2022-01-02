package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uit.thesis.assessment_mgnt.model.assessment.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query(value = "select d from Document d where d.survey.code = ?1")
    List<Document> getDocumentBySurveyCode(String surveyCode);
}
