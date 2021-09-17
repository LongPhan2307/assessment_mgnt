package uit.thesis.assessment_mgnt.service.human_resource;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.model.human_resource.Payslip;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class PayslipServiceImpl extends GenericServiceImpl<Payslip, Long> implements PayslipService {
}
