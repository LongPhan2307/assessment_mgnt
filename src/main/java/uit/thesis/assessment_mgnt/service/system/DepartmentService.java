package uit.thesis.assessment_mgnt.service.system;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.system.CreateDepartmentDto;
import uit.thesis.assessment_mgnt.model.system.Department;

import java.util.List;

public interface DepartmentService extends GenericService<Department, Long> {
    Department save(CreateDepartmentDto dto) throws Exception;

    List<Department> mockupData() throws NotFoundException;
}
