package uit.thesis.assessment_mgnt.controller.assessment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.ResponseFile;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.service.assessment.FileDBService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.assessment.FileDBDomain;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping(FileDBDomain.FILE)
@AllArgsConstructor
public class FileDBController {
    private FileDBService fileDBService;

    @PostMapping("")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file
                                             ){
//        if(errors.hasErrors())
//            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            FileDB fileDB = fileDBService.store(file);
            return ResponseObject.getResponse(fileDB, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

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
}
