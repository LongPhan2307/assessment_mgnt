package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    public Survey findByCode(String code);
}
