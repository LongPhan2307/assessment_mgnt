package uit.thesis.assessment_mgnt.service.system;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.system.CreateUserDto;
import uit.thesis.assessment_mgnt.model.system.Department;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.repository.system.DepartmentRepository;
import uit.thesis.assessment_mgnt.repository.system.RoleRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.UserStatus;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

import javax.transaction.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Override
    public User save(CreateUserDto dto) throws Exception {
        User user = new User();
        Department department = departmentRepository.findByName(dto.getDepartmentName());
//        Role role = roleRepository.findByName(dto.getRoleName());
//        if(role == null)
//            throw new Exception(ResponseMessage.UN_KNOWN("role Name"));
        if(department == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Department Name"));
        user = modelMapper.map(dto, User.class);
        user.setStatus(UserStatus.ACTIVE);
        user.setDepartment(department);
//        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User addRole(String roleName, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Username"));
        Role role = roleRepository.findByName(roleName);
        if(role == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Role Name"));
        user.addRole(role);

        return userRepository.save(user);
    }
}
