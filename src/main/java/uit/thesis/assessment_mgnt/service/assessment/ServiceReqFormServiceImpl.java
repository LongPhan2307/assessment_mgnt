//package uit.thesis.assessment_mgnt.service.assessment;
//
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
//import uit.thesis.assessment_mgnt.dto.assessment.svc_req_form.CreateSvcReqFrm;
//import uit.thesis.assessment_mgnt.model.assessment.ServiceReqForm;
//import uit.thesis.assessment_mgnt.repository.assessment.ServiceReqFormRepository;
//
//@Service
//@AllArgsConstructor
//public class ServiceReqFormServiceImpl extends GenericServiceImpl<ServiceReqForm, Long>
//        implements ServiceReqFormService {
//    private ServiceReqFormRepository serviceReqFormRepository;
//    private ModelMapper modelMapper;
//
//    @Override
//    public ServiceReqForm addNewServiceReqForm(CreateSvcReqFrm dto) {
//        ServiceReqForm serviceReqForm = new ServiceReqForm();
//        serviceReqForm = modelMapper.map(dto, ServiceReqForm.class);
//        return serviceReqFormRepository.save(serviceReqForm);
//    }
//}
