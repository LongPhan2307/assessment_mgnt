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

    @Query(value = "select DISTINCT survey.* \n" +
            "from public.assessment_survey as survey \n" +
            "inner join public.assessment_survey_inspector_link as ins on survey.id = ins.survey_id\n" +
            "inner join public.assessment_phase as p on survey.phase_name = p.name\n" +
            "where survey.director_username = ?1 or \n" +
            "survey.accountant_username = ?1 or \n" +
            "survey.manager_username = ?1 or exists (\n" +
            "\tselect r.* from public.assessment_user_role_link as l \n" +
            "\tinner join public.assessment_user as u on l.user_id = u.id\n" +
            "\tinner join public.assessment_role as r on l.role_id = u.id\n" +
            "\twhere u.id = ins.user_id and u.username = ?1 and r.name = p.role_name\n" +
            ")"
            , nativeQuery = true)
    List<Survey> findInDoingSurveysWithUsername(String username);

    @Query(value = "select DISTINCT survey.* \n" +
            "from public.assessment_survey as survey \n" +
            "inner join public.assessment_survey_inspector_link as ins on survey.id = ins.survey_id\n" +
            "inner join public.assessment_phase as p on survey.phase_name = p.name\n" +
            "where survey.director_username = ?1 or \n" +
            "survey.accountant_username = ?1 or \n" +
            "survey.manager_username = ?1 or exists (\n" +
            "\tselect r.* from public.assessment_user_role_link as l \n" +
            "\tinner join public.assessment_user as u on l.user_id = u.id\n" +
            "\tinner join public.assessment_role as r on l.role_id = u.id\n" +
            "\twhere u.id = ins.user_id and u.username = ?1 and r.name <> p.role_name\n" +
            ")"
            , nativeQuery = true)
    List<Survey> findDoneSurveysWithUsername(String username);

//    @Query("SELECT s FROM Survey s  WHERE ")
//    List<Survey> findByUsernameAndRole(String username, String roleName);

}
