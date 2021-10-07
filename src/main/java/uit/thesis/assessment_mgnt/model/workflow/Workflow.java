package uit.thesis.assessment_mgnt.model.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.assessment.Survey;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "assessment_workflow")
public class Workflow extends AbstractEntity {

    @Column(unique = true)
    private String name;

    private String description;


    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Survey> surveys = new HashSet<>();


    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Phase> phases = new HashSet<>();

    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL)
    private Set<PhaseLink> phaseLinks = new HashSet<>();
}
