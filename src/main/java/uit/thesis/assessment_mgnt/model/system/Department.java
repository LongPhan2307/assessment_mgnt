package uit.thesis.assessment_mgnt.model.system;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assessment_department")
@Getter
@Setter
public class Department extends AbstractEntity {
    private String name;

    private String description;
}
