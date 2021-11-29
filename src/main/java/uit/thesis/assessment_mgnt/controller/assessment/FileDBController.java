package uit.thesis.assessment_mgnt.controller.assessment;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.ResponseFile;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.service.assessment.FileDBService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.FileDBDomain;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("")
@AllArgsConstructor
public class FileDBController implements ServletContextAware {
    private ServletContext servletContext;
    private FileDBService fileDBService;

    @PostMapping(value = FileDBDomain.FILE + "/upload-files-document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFilesForDocument(@RequestParam MultipartFile[] files,
                                                         @RequestParam("documentId") long documentId){
        List<FileDB> list = new LinkedList<>();
        try {
            for(MultipartFile file : files){
//                String fileName = file.getOriginalFilename();
//                long fileSize = file.getSize();
//                String contentType = file.getContentType();
                FileDB fileDB = fileDBService.storeInDocument(file, documentId);
                list.add(fileDB);
            }
            if(list.isEmpty())
                return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
            return ResponseObject.getResponse(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObject.getResponse("Bug", HttpStatus.BAD_REQUEST);
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

    @PostMapping(value = FileDBDomain.FILE + "/files"
//            ,consumes = {"multipart/form-data"}

            )
    public ResponseEntity<Object> uploadMultiFiles(@RequestBody List<MultipartFile> files
    ){
        List<FileDB> list = null;
        try {
            list = fileDBService.storeFiles(files);
            return ResponseObject.getResponse(list, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(FileDBDomain.FILE)
    public ResponseEntity<Object> getAllFiles(){
        List<ResponseFile> list = fileDBService.getAllFiles().map(fileDB -> {
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

    @GetMapping(FileDBDomain.FILE + "/{id}")
    public ResponseEntity<Object> getFile(@PathVariable("id") String id){
        FileDB fileDB = fileDBService.getFileById(id);
        if(fileDB == null)
            return ResponseObject.getResponse(ResponseMessage.UN_KNOWN("FileDB "), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDB.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(new ByteArrayResource(fileDB.getData()));
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
