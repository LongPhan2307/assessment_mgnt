package uit.thesis.assessment_mgnt.model.system;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assessment_branch")
@Getter
@Setter
public class Branch extends AbstractEntity {
    private String name;

    @Column(unique = true)
    private String code;

    @Column(name = "head_quaters")
    private String headQuaters;

    private String address;
}
