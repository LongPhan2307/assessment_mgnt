package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uit.thesis.assessment_mgnt.model.system.Role;

import javax.persistence.TypedQuery;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

//    @EntityGraph(
//            attributePaths = {"users", "roles"},
//            type = EntityGraph.EntityGraphType.FETCH
//    )
    @Query(value = "SELECT r FROM Role r WHERE r.name = ?1")
    Role findUsersByRole(String roleName);

    @Query(value = "select r.*\n" +
            "from public.assessment_user_role_link as l inner join public.assessment_role as r on l.role_id = r.id \n" +
            "inner join public.assessment_user as u on l.user_id = u.id\n" +
            "where u.username = ?1\n" +
            "order by r.created_at asc\n" +
            "FETCH first 1 rows only"
    ,nativeQuery = true)
    Role getFirstRoleByUsername(String username);

}
