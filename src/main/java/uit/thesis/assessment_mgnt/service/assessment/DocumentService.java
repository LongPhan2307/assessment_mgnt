package uit.thesis.assessment_mgnt.service.assessment;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.document.CreateDocumentDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;

import java.util.List;

public interface DocumentService extends GenericService<Document, Long> {
    public Document addNew(CreateDocumentDto dto) throws Exception;

    public List<Document> getDocumentBySurveyCode(String surveyCode);
}
