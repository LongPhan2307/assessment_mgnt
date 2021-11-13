package uit.thesis.assessment_mgnt.controller.assessment;

//import io.swagger.models.Response;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateSurveyDto;
import uit.thesis.assessment_mgnt.dto.assessment.survey.ResponseSurvey;
import uit.thesis.assessment_mgnt.dto.assessment.survey.SurveyWithUsers;
import uit.thesis.assessment_mgnt.dto.assessment.survey.UpdateSurveyDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.service.assessment.SurveyService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.SurveyDomain;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(Domain.API)
public class SurveyController {
    private SurveyService surveyService;

    @GetMapping(SurveyDomain.SURVEY)
    public ResponseEntity<Object> findAll(){
        List<Survey> list =  surveyService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @GetMapping(SurveyDomain.SURVEY + "/fetch-survey-user")
    public ResponseEntity<Object> getSurveyWithUsers(){
        List<SurveyWithUsers> list =  surveyService.getAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }



//    @GetMapping(SurveyDomain.SURVEY + "/search")
//    public ResponseEntity<Object> searchSurveysByDirectorName(@RequestParam("director") String directorName){
//        List<Survey> list =  surveyService.getSurveysByDirector(directorName);
//        if(list.isEmpty())
//            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
//        return ResponseObject.getResponse(list, HttpStatus.OK);
//    }

    @PostMapping(Domain.API_ACCOUNTANT + SurveyDomain.SURVEY)
    public ResponseEntity<Object> addNewSurvey(@Valid @RequestBody CreateSurveyDto dto,
                                               BindingResult error){
        if(error.hasErrors())
            return ResponseObject.getResponse(error, HttpStatus.BAD_REQUEST);
        Survey survey= null;
        try {
            survey = surveyService.addNewSurvey(dto);
            return ResponseObject.getResponse(survey, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(SurveyDomain.SURVEY + "/search")
    public ResponseEntity<Object> findByCode(@RequestParam(value = "surveyCode") String code){
        Survey survey = surveyService.findByCode(code);
        if(survey == null)
            return ResponseObject.getResponse(ResponseMessage.UN_KNOWN("Survey "), HttpStatus.BAD_REQUEST);
        return ResponseObject.getResponse(survey, HttpStatus.OK);
    }

    @PutMapping(SurveyDomain.SURVEY + "/change")
    public ResponseEntity<Object> changeUserOfSurvey(@RequestParam(name = "accountant") String username,
                                                   @RequestParam("surveyCode") String surveyCode){
        try {
            Survey survey = surveyService.changeUserOfSurvey(username, surveyCode);
            return ResponseObject.getResponse(survey, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(Domain.API_MANAGER + SurveyDomain.SURVEY + "/assign")
    public ResponseEntity<Object> assignInspectors(@RequestParam("username") String[] arrUsername,
                                                   @RequestParam("surveyCode") String surveyCode){
        try {
            Survey survey = surveyService.assignInspectors(arrUsername, surveyCode);
            return ResponseObject.getResponse(survey, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping( Domain.API_ACCOUNTANT + SurveyDomain.SURVEY + "/update")
    public ResponseEntity<Object> updateSurveyInfo(@Valid @RequestBody UpdateSurveyDto dto,
                                                   BindingResult errors,
                                                   @RequestParam(value = "code") String code){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Survey survey = surveyService.updateSurvey(dto, code);
            return ResponseObject.getResponse(survey, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
