package uit.thesis.assessment_mgnt.controller.assessment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.CreateAssessmentCategoryDto;
import uit.thesis.assessment_mgnt.model.assessment.AssessmentCategory;
import uit.thesis.assessment_mgnt.service.assessment.AssessmentCategoryService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.assessment.AssessmentCategoryDomain;

import java.util.List;

@Controller
@RequestMapping(AssessmentCategoryDomain.ASSESSMENT_CATEGORY)
@AllArgsConstructor
public class AssessmentCategoryController {
    private AssessmentCategoryService assessmentCategoryService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<AssessmentCategory> list = assessmentCategoryService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addNewAssessmentCategory(@RequestBody CreateAssessmentCategoryDto dto,
                                                           BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        AssessmentCategory assessmentCategory = assessmentCategoryService.save(dto);
        return ResponseObject.getResponse(assessmentCategory, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getByName( @RequestParam( value = "name") String name){
        try {
            AssessmentCategory assessmentCategory = assessmentCategoryService.findByName(name);
            return ResponseObject.getResponse(assessmentCategory, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
