package uit.thesis.assessment_mgnt.model.system;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assessment_role")
@Getter
@Setter
public class Role extends AbstractEntity {
    @Column(unique = true)
    private String name;

    private String description;
}
