package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uit.thesis.assessment_mgnt.model.system.Role;

import javax.persistence.TypedQuery;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @EntityGraph(
            attributePaths = {"users", "roles"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query(value = "SELECT r FROM Role r WHERE r.name = ?1")
    Role findUsersByRole(String roleName);

}
