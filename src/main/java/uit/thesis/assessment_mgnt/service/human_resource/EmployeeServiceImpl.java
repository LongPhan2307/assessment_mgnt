package uit.thesis.assessment_mgnt.service.human_resource;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.human_resource.CreateEmployeeDto;
import uit.thesis.assessment_mgnt.model.human_resource.Employee;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.repository.human_resource.EmployeeRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Long> implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public Employee save(CreateEmployeeDto dto) throws Exception {
        Employee employee = new Employee();
        User user = userRepository.findByUsername(dto.getUsername());
        if(user == null)
            throw new Exception(ResponseMessage.UN_KNOWN("User "));
        if(user.getEmployeeProfile() != null)
            throw new Exception("EmployeeProfile is created");
        employee = modelMapper.map(dto, Employee.class);
        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(CreateEmployeeDto dto, String employeeId) throws Exception {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Employee"));
        employee = modelMapper.map(dto, Employee.class);
        return employeeRepository.save(employee);
    }
}
