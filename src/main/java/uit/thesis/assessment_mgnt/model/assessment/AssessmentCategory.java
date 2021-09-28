package uit.thesis.assessment_mgnt.model.assessment;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "assessment_category")
public class AssessmentCategory extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String name;

    private String description;
}
