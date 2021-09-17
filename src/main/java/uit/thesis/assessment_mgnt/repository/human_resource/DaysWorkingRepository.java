package uit.thesis.assessment_mgnt.repository.human_resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.human_resource.DaysWorking;

import java.util.List;

@Repository
public interface DaysWorkingRepository extends JpaRepository<DaysWorking, Long> {
    DaysWorking findByCode(String code);

    @Query(value = "SELECT * " +
            "FROM assessment_days_working d " +
            "INNER JOIN assessment_employee e " +
            "ON d.employee_employee_id = e.employee_id " +
            "WHERE e.employee_id = ?1 ", nativeQuery = true)
    List<DaysWorking> findAllByEmployeeId(String employeeId);

    @Query(value = "SELECT * " +
            "FROM assessment_days_working ", nativeQuery = true)
    List<DaysWorking> getAll();
}
