package uit.thesis.assessment_mgnt.model.assessment;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "assessment_survey_phase")
public class SurveyPhase extends AbstractEntity {
    @Column(unique = true)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Survey survey;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SurveyStatus status;
}
