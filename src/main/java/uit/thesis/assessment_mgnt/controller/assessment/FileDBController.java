package uit.thesis.assessment_mgnt.controller.assessment;

import lombok.AllArgsConstructor;
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
import uit.thesis.assessment_mgnt.utils.domain.assessment.FileDBDomain;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping(FileDBDomain.FILE)
@AllArgsConstructor
public class FileDBController implements ServletContextAware {
    private ServletContext servletContext;
    private FileDBService fileDBService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploads(@RequestParam MultipartFile[] files){
        try {
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                long fileSize = file.getSize();
                String contentType = file.getContentType();
                return ResponseObject.getResponse("OK", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObject.getResponse("Bug", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(
                                             @RequestParam("file") MultipartFile file
                                             ){

        try {
            FileDB fileDB = fileDBService.store(file);
            return ResponseObject.getResponse(fileDB, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/files"
//            ,consumes = {"multipart/form-data"}

            )
    public ResponseEntity<Object> uploadMultiFiles(@RequestBody List<MultipartFile> files
    ){
//        if(errors.hasErrors())
//            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        List<FileDB> list = null;
        try {
            list = fileDBService.storeFiles(files);
            return ResponseObject.getResponse(list, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
//        String message = "";
//        try {
//            List<String> fileNames = new ArrayList<>();
//
//            Arrays.asList(files).stream().forEach(file -> {
////                storageService.save(file);
//                fileNames.add(file.getOriginalFilename());
//            });
//
//            message = "Uploaded the files successfully: " + fileNames;
//            return null;
//        } catch (Exception e) {
//            message = "Fail to upload files!";
//            return null;
//        }
//    }

    @GetMapping("")
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
                    fileDB.getData().length);
        }).collect(Collectors.toList());
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
