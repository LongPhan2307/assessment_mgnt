package uit.thesis.assessment_mgnt.model.workflow;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.system.User;

import javax.persistence.*;

@Entity
@Table(name = "assessment_confirmation")
@Getter
@Setter
public class Confirmation extends AbstractEntity {
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "username")
    private User user;
}
