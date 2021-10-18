//package uit.thesis.assessment_mgnt.controller.workflow;
//
//import javassist.NotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import uit.thesis.assessment_mgnt.common.ResponseObject;
//import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseLinkDto;
//import uit.thesis.assessment_mgnt.dto.workflow.CreateWorkflowDto;
//import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;
//import uit.thesis.assessment_mgnt.model.workflow.Workflow;
//import uit.thesis.assessment_mgnt.service.workflow.PhaseLinkService;
//import uit.thesis.assessment_mgnt.utils.ResponseMessage;
//import uit.thesis.assessment_mgnt.utils.domain.workflow.PhaseLinkDomain;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping(PhaseLinkDomain.PHASE_LINK)
//public class PhaseLinkController {
//    private PhaseLinkService phaseLinkService;
//
//    @GetMapping("")
//    public ResponseEntity<Object> findAll(){
//        List<PhaseLink> list = phaseLinkService.findAll();
//        if(list.isEmpty())
//            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
//        return ResponseObject.getResponse(list, HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<Object> addWorkflow(@Valid @RequestBody CreatePhaseLinkDto dto,
//                                              BindingResult errors){
//        if(errors.hasErrors())
//            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
//        try {
//            PhaseLink phaseLink = phaseLinkService.addPhaseLink(dto);
//            return ResponseObject.getResponse(phaseLink, HttpStatus.CREATED);
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//}
