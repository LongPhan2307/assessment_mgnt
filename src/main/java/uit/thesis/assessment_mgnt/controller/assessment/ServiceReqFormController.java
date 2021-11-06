//package uit.thesis.assessment_mgnt.controller.assessment;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import uit.thesis.assessment_mgnt.common.ResponseObject;
//import uit.thesis.assessment_mgnt.dto.assessment.svc_req_form.CreateSvcReqFrm;
//import uit.thesis.assessment_mgnt.service.assessment.ServiceReqFormService;
//import uit.thesis.assessment_mgnt.utils.ResponseMessage;
//import uit.thesis.assessment_mgnt.utils.domain.assessment.ServiceReqFormDomain;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping(ServiceReqFormDomain.SERVICE_REQ_FORM)
//@AllArgsConstructor
//public class ServiceReqFormController {
//    private ServiceReqFormService serviceReqFormService;
//
//    @GetMapping("")
//    public ResponseEntity<Object> findAll(){
//        List<ServiceReqForm> list = serviceReqFormService.findAll();
//        if(list.isEmpty())
//            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
//        return ResponseObject.getResponse(list, HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<Object> addNewSvcReqFrm(@Valid @RequestBody CreateSvcReqFrm dto,
//                                                  BindingResult error){
//        if(error.hasErrors())
//            return ResponseObject.getResponse(error, HttpStatus.BAD_REQUEST);
//        ServiceReqForm serviceReqForm = serviceReqFormService.addNewServiceReqForm(dto);
//        return ResponseObject.getResponse(serviceReqForm, HttpStatus.CREATED);
//    }
//
//}
