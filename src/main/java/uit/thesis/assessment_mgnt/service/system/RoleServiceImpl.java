package uit.thesis.assessment_mgnt.service.system;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.system.CreateRoleDto;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.repository.system.RoleRepository;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Override
    public Role save(CreateRoleDto dto) {
        Role role = new Role();
        role = modelMapper.map(dto, Role.class);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> mockupData() {
        List<Role> list = new LinkedList<>();
        list.add(new Role(RoleName.DIRECTOR.toString()));
        list.add(new Role(RoleName.ADMIN.toString()));
        list.add(new Role(RoleName.ACCOUNTANT.toString()));
        list.add(new Role(RoleName.MANAGER.toString()));
        list.add(new Role(RoleName.INSPECTOR.toString()));
        return roleRepository.saveAll(list);
    }
}
