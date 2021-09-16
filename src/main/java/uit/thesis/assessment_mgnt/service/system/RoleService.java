package uit.thesis.assessment_mgnt.service.system;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.system.CreateRoleDto;
import uit.thesis.assessment_mgnt.model.system.Role;

public interface RoleService extends GenericService<Role, Long> {
    Role save(CreateRoleDto dto);
}
