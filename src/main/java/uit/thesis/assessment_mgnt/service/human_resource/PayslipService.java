package uit.thesis.assessment_mgnt.service.human_resource;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.human_resource.CreatePayslipDto;
import uit.thesis.assessment_mgnt.model.human_resource.Payslip;

public interface PayslipService extends GenericService<Payslip, Long> {
    Payslip save(CreatePayslipDto dto) throws Exception;

    Payslip update(CreatePayslipDto dto, String code) throws Exception;
}
