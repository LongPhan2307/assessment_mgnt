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

//    @Query(value = "select code, name, status_form, phase_name" +
//            " from public.assessment_survey", nativeQuery = true)
    @Query("SELECT suvey.status FROM Survey suvey ")
    public List<ResponseSurvey> getAll();

    @Query("SELECT suvey FROM Survey suvey WHERE suvey.status not in (uit.thesis.assessment_mgnt.utils.survey.Status.CANCELED " +
            ",uit.thesis.assessment_mgnt.utils.survey.Status.CLOSED) ")
    List<Survey> getINPROGRESSSurvey();

    @Query("SELECT s FROM Survey s WHERE s.director.username = ?1")
    List<Survey> findByDirectorName(String directorName);

    @Query(value = "select DISTINCT survey.*\n" +
            "from public.assessment_survey as survey\n" +
            "where survey.status <> 'CLOSED' and survey.status <> 'CANCELED' and exists\n" +
            "(\n" +
            "select s.id\n" +
            "from public.assessment_survey as s\n" +
            "where s.director_username = 'manager' or\n" +
            "s.accountant_username = 'manager' or\n" +
            "s.manager_username = 'manager' or exists (\n" +
            "\tselect u.* from public.assessment_user as u\n" +
            "\tinner join public.assessment_survey_inspector_link as ins on u.id = ins.user_id\n" +
            "\twhere s.id = ins.survey_id and u.username = 'manager'\n" +
            "))", nativeQuery = true)
    List<Survey> findSurveysWithUsername(String username);

//    @Query("SELECT s FROM Survey s  WHERE ")
//    List<Survey> findByUsernameAndRole(String username, String roleName);

}
