package uit.thesis.assessment_mgnt.model.human_resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.utils.SalaryStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "assessment_payslip")
@Getter
@Setter
public class Payslip extends AbstractEntity {
    @Column(unique = true)
    private String code;

    @Column(name = "total_salary")
    private BigDecimal totalSalary;
    @Column(name = "salary_description")
    private String salaryDescription;

    @Min(value = 0)
    private BigDecimal bonus;

    @Column(name = "bonus_description")
    private String bonusDescription;

    @Column(name = "penalty_pay")
    @Min(value = 0)
    private BigDecimal penaltyPay;

    @Column(name = "penalty_pay_description")
    private String penaltyPayDescription;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SalaryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "employeeId")
    @JsonIgnore
    private Employee employee;
}
