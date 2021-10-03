package uit.thesis.assessment_mgnt.service.assessment;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.CreateAssessmentCategoryDto;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;

public interface AssessmentCategoryService extends GenericService<AssessmentCategory, Long> {
    public AssessmentCategory save(CreateAssessmentCategoryDto dto);

    public AssessmentCategory findByName(String name) throws Exception;
}
