package uit.thesis.assessment_mgnt.service.system;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.system.CreateUserDto;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

import java.util.List;

public interface UserService extends GenericService<User, Long> {
    User save(CreateUserDto dto) throws Exception;

    User addRole(String roleName, String username) throws Exception;

    List<User> mockupData();
}
