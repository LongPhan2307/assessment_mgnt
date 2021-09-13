package uit.thesis.assessment_mgnt.model.human_resource;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.utils.SalaryStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "assessment_salary")
@Getter
@Setter
public class Salary extends AbstractEntity {
    @Column(name = "total_salary")
    private BigDecimal totalSalary;
    @Column(name = "salary_description")
    private String salaryDescription;

    private BigDecimal bonus;

    @Column(name = "bonus_description")
    private String bonusDescription;

    @Column(name = "penalty_pay")
    private BigDecimal penaltyPay;

    @Column(name = "penalty_pay_description")
    private String penaltyPayDescription;

    @Enumerated(EnumType.STRING)
    private SalaryStatus status;
}
