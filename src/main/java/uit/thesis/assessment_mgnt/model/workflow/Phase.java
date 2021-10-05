package uit.thesis.assessment_mgnt.model.workflow;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "assessment_phase")
@Getter
@Setter
public class Phase extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String name;

    @Column(name = "node_order")
    private int nodeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    private Workflow workflow;

    @OneToOne(mappedBy = "linkBy", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, optional = false)
    private PhaseLink phaseBy;

    @OneToOne(mappedBy = "linkTo", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, optional = false)
    private PhaseLink phaseTo;
}
