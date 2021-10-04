package uit.thesis.assessment_mgnt.service.assessment;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.document.CreateDocumentDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;

public interface DocumentService extends GenericService<Document, Long> {
    public Document addNew(CreateDocumentDto dto) throws Exception;
}
