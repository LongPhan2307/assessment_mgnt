package uit.thesis.assessment_mgnt.service.system;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.system.CreateRoleDto;
import uit.thesis.assessment_mgnt.model.system.Role;
import uit.thesis.assessment_mgnt.repository.system.RoleRepository;

import javax.transaction.Transactional;

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
}
