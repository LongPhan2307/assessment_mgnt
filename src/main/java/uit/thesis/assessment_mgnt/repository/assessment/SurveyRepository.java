package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.dto.assessment.survey.ResponseSurvey;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    public Survey findByCode(String code);

    @Query(value = "select code, name, status_form, phase_name" +
            " from public.assessment_survey", nativeQuery = true)
    public List<ResponseSurvey> getAll();

    @Query("SELECT s FROM Survey s WHERE s.director.username = ?1")
    List<Survey> findByDirectorName(String directorName);

//    @Query("SELECT s FROM Survey s  WHERE ")
//    List<Survey> findByUsernameAndRole(String username, String roleName);

}
