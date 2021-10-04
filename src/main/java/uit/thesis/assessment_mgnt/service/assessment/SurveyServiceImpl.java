package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateSurveyDto;
import uit.thesis.assessment_mgnt.dto.assessment.survey.UpdateSurveyDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class SurveyServiceImpl extends GenericServiceImpl<Survey, Long> implements SurveyService {
    private SurveyRepository surveyRepository;
    private ModelMapper modelMapper;
    private CertificateService certificateService;


    @Override
    public Survey addNewSurvey(CreateSurveyDto dto) {
        Survey survey = new Survey();
        survey = modelMapper.map(dto, Survey.class);
        survey.setCode(RandomStringUtils.randomAlphanumeric(10));
        survey.setStatusForm(StatusForm.PENDING);
        certificateService.generateCertificateCode();
        return surveyRepository.save(survey);
    }

    @Override
    public List<StatusForm> getAllStatusForm() {
        List<StatusForm> list = Arrays.asList(StatusForm.values());
        return list;
    }

    @Override
    public Survey updateSurvey(UpdateSurveyDto dto, String code) throws Exception {
        Survey survey = surveyRepository.findByCode(code);
        if(code == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Survey Code"));
        this.modelMapper.map(dto, survey);
//        survey = this.surveyRepository.save(survey);
        survey.setCode(code);
        return this.surveyRepository.save(survey);
    }
}
