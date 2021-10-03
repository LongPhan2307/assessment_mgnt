package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.CreateAssessmentCategoryDto;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;
import uit.thesis.assessment_mgnt.repository.assessment.AssessmentCategoryRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class AssessmentCategoryServiceImpl extends GenericServiceImpl<AssessmentCategory, Long>
        implements AssessmentCategoryService {
    private AssessmentCategoryRepository assessmentCategoryRepository;
    private ModelMapper mapper;


    @Override
    public AssessmentCategory save(CreateAssessmentCategoryDto dto) {
        AssessmentCategory assessmentCategory = new AssessmentCategory();
        assessmentCategory = mapper.map(dto, AssessmentCategory.class);
        assessmentCategory.setCode(RandomStringUtils.randomAlphanumeric(10));
        return assessmentCategoryRepository.save(assessmentCategory);
    }

    @Override
    public AssessmentCategory findByName(String name) throws Exception {
        AssessmentCategory assessmentCategory = assessmentCategoryRepository.findByName(name);
        if(assessmentCategory == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Name"));

        return assessmentCategory;
    }
}
