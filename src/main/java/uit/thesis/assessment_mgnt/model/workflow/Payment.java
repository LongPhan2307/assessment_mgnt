package uit.thesis.assessment_mgnt.model.workflow;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "assessment_payment")
@Getter
@Setter
public class Payment extends AbstractEntity {
    private String name;

    private BigDecimal amountPaid;

    private BigDecimal debt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Customer customer;
}
