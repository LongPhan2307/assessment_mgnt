package uit.thesis.assessment_mgnt.model.human_resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.utils.EmployeeDegree;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private EmployeeDegree degree;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Payslip> listSalary = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DaysWorking> daysWorking = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private User user;

}
