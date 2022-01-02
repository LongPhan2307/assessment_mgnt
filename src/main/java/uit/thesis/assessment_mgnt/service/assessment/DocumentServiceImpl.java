package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.document.CreateDocumentDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.repository.assessment.DocumentRepository;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.service.workflow.CommentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentServiceImpl extends GenericServiceImpl<Document, Long> implements DocumentService {
    private DocumentRepository documentRepository;
    private ModelMapper modelMapper;
    private SurveyRepository surveyRepository;
    private CommentService commentService;
    private UserRepository userRepository;

    @Override
    public Document addNew(CreateDocumentDto dto) throws Exception {
        User user =  userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        if(survey == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Survey Code"));
        Document document = new Document();
        document = modelMapper.map(dto, Document.class);
        document.setSurvey(survey);
        commentService.generateComment(document, survey, user);
        return documentRepository.save(document);
    }

    @Override
    public List<Document> getDocumentBySurveyCode(String surveyCode) {
        return documentRepository.getDocumentBySurveyCode(surveyCode);
    }
}
