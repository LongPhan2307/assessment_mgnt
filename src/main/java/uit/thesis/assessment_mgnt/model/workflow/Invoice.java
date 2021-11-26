package uit.thesis.assessment_mgnt.model.workflow;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "assessment_invoice")
public class Invoice extends AbstractEntity {
    private String name;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<FileDB> files = new HashSet<>();
}
