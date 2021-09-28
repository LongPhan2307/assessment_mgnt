package uit.thesis.assessment_mgnt.model.assessment;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "assessment_survey_status")
public class SurveyStatus extends AbstractEntity {
    private String phase;

    private String status;
}
