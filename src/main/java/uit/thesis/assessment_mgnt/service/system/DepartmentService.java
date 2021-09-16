package uit.thesis.assessment_mgnt.service.system;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.system.CreateDepartmentDto;
import uit.thesis.assessment_mgnt.model.system.Department;

public interface DepartmentService extends GenericService<Department, Long> {
    Department save(CreateDepartmentDto dto) throws Exception;

}
