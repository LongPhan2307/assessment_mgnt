package uit.thesis.assessment_mgnt.service.human_resource;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.human_resource.CreatePayslipDto;
import uit.thesis.assessment_mgnt.model.human_resource.Employee;
import uit.thesis.assessment_mgnt.model.human_resource.Payslip;
import uit.thesis.assessment_mgnt.repository.human_resource.EmployeeRepository;
import uit.thesis.assessment_mgnt.repository.human_resource.PayslipRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.SalaryStatus;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional
public class PayslipServiceImpl extends GenericServiceImpl<Payslip, Long> implements PayslipService {
    private PayslipRepository payslipRepository;
    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;

    @Override
    public Payslip save(CreatePayslipDto dto) throws Exception {
        Payslip payslip = new Payslip();
        Employee employee = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        if(employee == null)
            throw new Exception(ResponseMessage.UN_KNOWN("EmployeeID"));
        payslip = modelMapper.map(dto, Payslip.class);
        BigDecimal total = new BigDecimal(0);
        payslip.setCode(RandomStringUtils.randomAlphanumeric(6));
        total = employee.getBaseSalary()
                            .add(dto.getBonus())
                            .subtract(dto.getPenaltyPay());
        payslip.setTotalSalary(total);
        payslip.setStatus(SalaryStatus.PENDING);
        payslip.setEmployee(employee);
        return payslipRepository.save(payslip);
    }

    @Override
    public Payslip update(CreatePayslipDto dto, String code) throws Exception {

        Payslip payslip = payslipRepository.findByCode(code);
        if( payslip == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Payslip Code"));
        Employee employee = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        if(employee == null)
            throw new Exception(ResponseMessage.UN_KNOWN("EmployeeID"));
        payslip.setPenaltyPay(dto.getPenaltyPay());
        payslip.setPenaltyPayDescription(dto.getPenaltyPayDescription());
        payslip.setBonus(dto.getBonus());
        payslip.setBonusDescription(dto.getBonusDescription());
        payslip.setSalaryDescription(dto.getSalaryDescription());
        BigDecimal total = new BigDecimal(0);
        total = employee.getBaseSalary()
                .add(dto.getBonus())
                .subtract(dto.getPenaltyPay());
        payslip.setTotalSalary(total);
        payslip.setStatus(SalaryStatus.PENDING);
        payslip.setEmployee(employee);
        return payslipRepository.save(payslip);
    }


}
