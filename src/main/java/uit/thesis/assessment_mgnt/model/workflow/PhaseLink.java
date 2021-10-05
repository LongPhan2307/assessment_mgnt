package uit.thesis.assessment_mgnt.model.workflow;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "assessment_phase_link")
@Getter
@Setter
public class PhaseLink extends AbstractEntity {

    private String transition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    private Workflow workflow;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_by_id")
    private Phase linkBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_to_id")
    private Phase linkTo;
}
