package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.document.CreateDocumentDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.repository.assessment.DocumentRepository;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class DocumentServiceImpl extends GenericServiceImpl<Document, Long> implements DocumentService {
    private DocumentRepository documentRepository;
    private ModelMapper modelMapper;
    private SurveyRepository surveyRepository;

    @Override
    public Document addNew(CreateDocumentDto dto) throws Exception {
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        if(survey == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Survey Code"));
        Document document = new Document();
        document = modelMapper.map(dto, Document.class);
//        document.setSurvey(survey);
        return documentRepository.save(document);
    }
}
