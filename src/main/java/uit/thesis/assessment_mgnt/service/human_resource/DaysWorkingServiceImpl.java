package uit.thesis.assessment_mgnt.service.human_resource;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.human_resource.CreateDaysWorkingDto;
import uit.thesis.assessment_mgnt.model.human_resource.DaysWorking;
import uit.thesis.assessment_mgnt.model.human_resource.Employee;
import uit.thesis.assessment_mgnt.repository.human_resource.DaysWorkingRepository;
import uit.thesis.assessment_mgnt.repository.human_resource.EmployeeRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import org.apache.commons.lang3.RandomStringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DaysWorkingServiceImpl extends GenericServiceImpl<DaysWorking, Long> implements DaysWorkingService {
    private EmployeeRepository employeeRepository;
    private DaysWorkingRepository daysWorkingRepository;
    private ModelMapper modelMapper;

    @Override
    public DaysWorking save(CreateDaysWorkingDto dto) throws Exception {
        DaysWorking daysWorking = new DaysWorking();
        Employee employee = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        if(employee == null)
            throw new Exception(ResponseMessage.UN_KNOWN("EmployeeID"));
        daysWorking = modelMapper.map(dto, DaysWorking.class);
        daysWorking.setCode(RandomStringUtils.randomAlphanumeric(6));
        daysWorking.setEmployee(employee);
        return daysWorkingRepository.save(daysWorking);
    }

    @Override
    public DaysWorking update(CreateDaysWorkingDto dto, String code) throws Exception {
        DaysWorking daysWorking = daysWorkingRepository.findByCode(code);
        if(code == null)
            throw new Exception(ResponseMessage.UN_KNOWN("DaysWorking Code"));
        Employee employee = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        if(employee == null)
            throw new Exception(ResponseMessage.UN_KNOWN("EmployeeID"));
        daysWorking = modelMapper.map(dto, DaysWorking.class);
        daysWorking.setEmployee(employee);
        return daysWorkingRepository.save(daysWorking);
    }

    @Override
    public List<DaysWorking> getAllDaysWorkingByEmployeeId(String employeeId) {
        List<DaysWorking> list = daysWorkingRepository.findAllByEmployeeId(employeeId);
        return list;
    }

    @Override
    public List<DaysWorking> getAll() {
        List<DaysWorking> list = daysWorkingRepository.getAll();
        return list;
    }
}
