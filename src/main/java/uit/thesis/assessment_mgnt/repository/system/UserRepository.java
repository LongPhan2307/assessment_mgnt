package uit.thesis.assessment_mgnt.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.system.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from public.assessment_user as u inner join public.assessment_user_role_link as l on u.id = l.user_id\n" +
            "where l.role_id = 5")
    List<User> getAllInspectors();

    @Query(nativeQuery = true, value = "select  u.*\n" +
            " from public.assessment_user_role_link as l inner join public.assessment_user as u ON l.user_id = u.id\n" +
            "inner join public.assessment_role as r on r.id = l.role_id\n" +
            "where r.\"name\" = 'MANAGER'")
    List<User> getAllManager();

    @Query(nativeQuery = true, value = "select  u.*\n" +
            " from public.assessment_user_role_link as l inner join public.assessment_user as u ON l.user_id = u.id\n" +
            "inner join public.assessment_role as r on r.id = l.role_id\n" +
            "where r.\"name\" = 'ACCOUNTANT'")
    List<User> getAllAccountant();

    @Query(nativeQuery = true, value = "select  u.*\n" +
            " from public.assessment_user_role_link as l inner join public.assessment_user as u ON l.user_id = u.id\n" +
            "inner join public.assessment_role as r on r.id = l.role_id\n" +
            "where r.\"name\" = 'DIRECTOR'")
    List<User> getAllDirector();
}
