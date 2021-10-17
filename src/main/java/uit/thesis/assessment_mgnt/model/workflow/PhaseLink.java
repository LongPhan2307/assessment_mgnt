package uit.thesis.assessment_mgnt.model.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    private Workflow workflow;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Phase linkBy;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Phase linkTo;
}
