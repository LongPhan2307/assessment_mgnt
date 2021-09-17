package uit.thesis.assessment_mgnt.service.human_resource;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.human_resource.CreateEmployeeDto;
import uit.thesis.assessment_mgnt.model.human_resource.Employee;

public interface EmployeeService extends GenericService<Employee, Long> {
    Employee save(CreateEmployeeDto dto) throws Exception;

    Employee update(CreateEmployeeDto dto, String employeeId) throws Exception;
}
