package uit.thesis.assessment_mgnt.model.human_resource;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "assessment_employee")
@Getter
@Setter
public class Employee extends AbstractEntity {
    @Column(unique = true)
    private String employeeId;

    private String fullname;

    private String address;

    private boolean gender;

    private String phone;

    private boolean marriage;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;

}
