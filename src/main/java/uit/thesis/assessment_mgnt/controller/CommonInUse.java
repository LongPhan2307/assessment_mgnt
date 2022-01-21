package uit.thesis.assessment_mgnt.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.ResponseFile;
import uit.thesis.assessment_mgnt.dto.assessment.document.CreateDocumentDto;
import uit.thesis.assessment_mgnt.dto.system.ResponseUserDto;
import uit.thesis.assessment_mgnt.dto.workflow.CreateCommentDto;
import uit.thesis.assessment_mgnt.dto.workflow.UpdatePhaseDto;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateInvoiceDto;
import uit.thesis.assessment_mgnt.dto.workflow.invoice.CreateSurchargeDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;
import uit.thesis.assessment_mgnt.service.assessment.DocumentService;
import uit.thesis.assessment_mgnt.service.assessment.FileDBService;
import uit.thesis.assessment_mgnt.service.system.UserService;
import uit.thesis.assessment_mgnt.service.workflow.CommentService;
import uit.thesis.assessment_mgnt.service.workflow.ConfirmationService;
import uit.thesis.assessment_mgnt.service.workflow.InvoiceService;
import uit.thesis.assessment_mgnt.service.workflow.PhaseService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.UserDomain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.DocumentDomain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.FileDBDomain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.CommentDomain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.ConfirmationDomain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.InvoiceDomain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.PhaseDomain;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Domain.API)
@AllArgsConstructor
public class CommonInUse {
    private UserService userService;
    private PhaseService phaseService;
    private DocumentService documentService;
    private CommentService commentService;
    private FileDBService fileDBService;
    private InvoiceService invoiceService;
    private ConfirmationService confirmationService;

    @GetMapping(UserDomain.USER_DOMAIN + "/fetch")
    public ResponseEntity<Object> getUserInfoByUsername(@RequestParam("username") String username){
        try {
            ResponseUserDto user = userService.getUserByUsername(username);
            return ResponseObject.getResponse(user, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(PhaseDomain.PHASE + "/submit-phase")
    public ResponseEntity<Object> submitPhase(@RequestBody UpdatePhaseDto dto,
                                              BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Survey res = phaseService.submitPhase(dto);
            return ResponseObject.getResponse(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(PhaseDomain.PHASE + "/return-phase")
    public ResponseEntity<Object> returnPhase(@RequestBody UpdatePhaseDto dto,
                                              BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Survey res = phaseService.returnPhase(dto);
            return ResponseObject.getResponse(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping(CommentDomain.COMMENT_DOMAIN + "/search/survey")
    public ResponseEntity<Object> findBySurvey(@RequestParam("survey") String surveyCode){
        List<Comment> comments = null;
        try {
            comments = commentService.findBySurvey(surveyCode);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if(comments.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(comments, HttpStatus.OK);
    }

    @PostMapping(CommentDomain.COMMENT_DOMAIN)
    public ResponseEntity<Object> addComment(@RequestBody CreateCommentDto dto,
                                             BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Comment comment = commentService.addComment(dto);
            return ResponseObject.getResponse(comment, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = FileDBDomain.FILE + "/upload-file-document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFileForDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentId") long documentId
    ){

        try {
            FileDB fileDB = fileDBService.storeInDocument(file, documentId);
            return ResponseObject.getResponse(fileDB, HttpStatus.CREATED);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = FileDBDomain.FILE + "/upload-files-certificate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFileForCertificate(
            @RequestParam("file") MultipartFile file,
            @RequestParam("certificate") String certificateCode
    ){

        try {
            FileDB fileDB = fileDBService.storeInCertificate(file, certificateCode);
            return ResponseObject.getResponse(fileDB, HttpStatus.CREATED);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(FileDBDomain.FILE + "/search")
    public ResponseEntity<Object> getAllFilesByCondition(@RequestParam(name = "documentId", required = false) Long documentId,
                                                         @RequestParam(name = "invoiceId", required = false) Long invoiceId,
                                                         @RequestParam(name = "certificateId", required = false) Long certificateId){
        List<ResponseFile> list = fileDBService.getAllFilesByCondition(documentId, invoiceId, certificateId).map(fileDB -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(FileDBDomain.FILE + "/")
                    .path(fileDB.getId())
                    .toUriString();

            return new ResponseFile(
                    fileDB.getName(),
                    fileDownloadUri,
                    fileDB.getType(),
                    fileDB.getData().length
            );
        }).collect(Collectors.toList());
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(Domain.API_ACCOUNTANT + InvoiceDomain.INVOICE_DOMAIN)
    public ResponseEntity<Object> createInvoice(@RequestBody CreateInvoiceDto dto,
                                                BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Invoice invoice = invoiceService.createInvoice(dto);
            return ResponseObject.getResponse(invoice, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(Domain.API_ACCOUNTANT + InvoiceDomain.INVOICE_DOMAIN + "/surcharge")
    public ResponseEntity<Object> addNewSurcharge(@RequestBody CreateSurchargeDto dto,
                                                  BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Invoice invoice = invoiceService.addNewSurcharge(dto);
            return ResponseObject.getResponse(invoice, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(ConfirmationDomain.CONFIRMATION_DOMAIN + "/search")
    public ResponseEntity<Object> findByCommentId(@RequestParam("comment") long commentId){
        List<Confirmation> list = confirmationService.findByCommentId(commentId);
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PutMapping(ConfirmationDomain.CONFIRMATION_DOMAIN + "/change-status")
    public ResponseEntity<Object> changeStatusConfirm(@RequestParam("comment") long commentId
    ){
        try {
            Comment comment = confirmationService.changeStatusConfirmation(commentId);
            return ResponseObject.getResponse(comment, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
