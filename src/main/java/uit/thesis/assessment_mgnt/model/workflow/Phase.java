package uit.thesis.assessment_mgnt.model.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assessment_phase")
@Getter
@Setter
@NoArgsConstructor
public class Phase extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @Column(name = "node_order", unique = true)
    private int nodeOrder;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(referencedColumnName = "name")
//    private Workflow workflow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    @JsonIgnore
    private Role role;

//    @OneToOne(mappedBy = "linkBy"
//            )
//    @JsonIgnore
//    private PhaseLink phaseBy;
//
//    @OneToOne(mappedBy = "linkTo"
//            )
//    @JsonIgnore
//    private PhaseLink phaseTo;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Survey> surveys = new HashSet<>();

    public Phase(String name, int nodeOrder, Role role){
        this.name = name;
        this.nodeOrder = nodeOrder;
        this.role = role;
    }
}
