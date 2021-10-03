package uit.thesis.assessment_mgnt.service.assessment;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateSurveyDto;
import uit.thesis.assessment_mgnt.dto.assessment.survey.UpdateSurveyDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import java.util.List;

public interface SurveyService extends GenericService<Survey, Long> {
    public Survey addNewSurvey(CreateSurveyDto dto);

    public List<StatusForm> getAllStatusForm();

    public Survey updateSurvey(UpdateSurveyDto dto, String code) throws Exception;
}
