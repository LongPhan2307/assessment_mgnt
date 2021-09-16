package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.thesis.assessment_mgnt.model.system.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
