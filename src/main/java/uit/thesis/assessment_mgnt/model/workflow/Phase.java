package uit.thesis.assessment_mgnt.model.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assessment_phase")
@Getter
@Setter
public class Phase extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @Column(name = "node_order")
    private int nodeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    private Workflow workflow;

    @OneToOne(mappedBy = "linkBy", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private PhaseLink phaseBy;

    @OneToOne(mappedBy = "linkTo", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private PhaseLink phaseTo;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Survey> surveys = new HashSet<>();
}
