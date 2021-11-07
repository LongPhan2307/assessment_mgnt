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

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<AssessmentCategory> mockupData() {
        List<AssessmentCategory> list = new LinkedList<>();
        list.add(new AssessmentCategory("Giám định hàng lỏng"));
        list.add(new AssessmentCategory("Giám định tình trạng hầm hàng"));
        list.add(new AssessmentCategory("Giám định tổn thất"));
        return assessmentCategoryRepository.saveAll(list);
    }
}
