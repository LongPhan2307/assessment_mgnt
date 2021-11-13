package uit.thesis.assessment_mgnt.controller.assessment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.document.CreateDocumentDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;
import uit.thesis.assessment_mgnt.service.assessment.DocumentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.DocumentDomain;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(Domain.API)
@AllArgsConstructor
public class DocumentController {
    private DocumentService documentService;

    @GetMapping(DocumentDomain.DOCUMENT)
    public ResponseEntity<Object> findAll(){
        List<Document> list = documentService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(DocumentDomain.DOCUMENT)
    public ResponseEntity<Object> addDocument(@Valid @RequestBody CreateDocumentDto dto,
                                              BindingResult error){
        if(error.hasErrors())
            return ResponseObject.getResponse(error, HttpStatus.BAD_REQUEST);
        try {
            Document document = documentService.addNew(dto);
            return ResponseObject.getResponse(document, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
