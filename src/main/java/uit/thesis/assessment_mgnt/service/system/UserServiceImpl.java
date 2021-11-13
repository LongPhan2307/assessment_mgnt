package uit.thesis.assessment_mgnt.service.system;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
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

    @Override
    public List<User> mockupData() {
        List<User> list = new LinkedList<>();
        List<Role> roles = roleRepository.findAll();
        Department itDepartment = departmentRepository.findByName("IT");
        Department directorDepartment = departmentRepository.findByName(RoleName.DIRECTOR.toString());
        Department accountantDepartment = departmentRepository.findByName(RoleName.ACCOUNTANT.toString());
        Department assessmentDepartment = departmentRepository.findByName("ASSESSMENT");
        list.add(new User("director", "director@gmail.com",passwordEncoder.encode("123"), directorDepartment, roles.get(0)));
        list.add(new User("director1", "director1@gmail.com",passwordEncoder.encode("123"), directorDepartment, roles.get(0)));
        list.add(new User("director2", "director2@gmail.com",passwordEncoder.encode("123"), directorDepartment, roles.get(0)));
        list.add(new User("admin", "admin@gmail.com",passwordEncoder.encode("123"), itDepartment,roles.get(1)));
        list.add(new User("accountant", "accountant@gmail.com",passwordEncoder.encode("123"), accountantDepartment,roles.get(2)));
        list.add(new User("accountant1", "accountant1@gmail.com",passwordEncoder.encode("123"), accountantDepartment, roles.get(2)));
        list.add(new User("accountant2", "accountant2@gmail.com",passwordEncoder.encode("123"), accountantDepartment, roles.get(2)));
        list.add(new User("manager", "manager@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(3)));
        list.add(new User("manager2", "manager2@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(3)));
        list.add(new User("manager1", "manager1@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(3)));
        list.add(new User("anh", "anh@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(4)));
        list.add(new User("anh1", "anh1@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(4)));
        list.add(new User("boi", "boi@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(4)));
        list.add(new User("boi1", "boi1@gmail.com",passwordEncoder.encode("123"), assessmentDepartment, roles.get(4)));
        return userRepository.saveAll(list);
    }
}
