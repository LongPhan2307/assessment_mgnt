package uit.thesis.assessment_mgnt.service.assessment;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateSurveyDto;
import uit.thesis.assessment_mgnt.dto.assessment.survey.ResponseSurvey;
import uit.thesis.assessment_mgnt.dto.assessment.survey.SurveyWithUsers;
import uit.thesis.assessment_mgnt.dto.assessment.survey.UpdateSurveyDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import java.util.List;

public interface SurveyService extends GenericService<Survey, Long> {
    public Survey addNewSurvey(CreateSurveyDto dto) throws NotFoundException;

    public List<StatusForm> getAllStatusForm();

    public Survey findByCode(String code);

    List<Survey> getSurveysByDirector(String directorName);

    public List<ResponseSurvey> getAllSurveyCode();

    public List<SurveyWithUsers> getAll();

    public Survey changeUserOfSurvey(String username, String surveyCode) throws Exception;

    public Survey assignInspectors(String[] usernames, String surveyCode) throws NotFoundException;

    public Survey assigneeApproval(String[] arrUsername, String surveyCode) throws NotFoundException;

    public Survey updateSurvey(UpdateSurveyDto dto, String code) throws Exception;
}
