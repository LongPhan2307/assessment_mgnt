package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;

@Repository
public interface AssessmentCategoryRepository extends JpaRepository<AssessmentCategory, Long> {
    public AssessmentCategory findByName(String name);

    public AssessmentCategory findByCode(String code);
}
