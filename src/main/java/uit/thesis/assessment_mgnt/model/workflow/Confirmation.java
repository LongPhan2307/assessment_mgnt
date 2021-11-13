package uit.thesis.assessment_mgnt.model.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.system.User;

import javax.persistence.*;

@Entity
@Table(name = "assessment_confirmation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Confirmation extends AbstractEntity {
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "username")
    private User user;

    public Confirmation(Comment comment, User user){
        this.status = false;
        this.comment = comment;
        this.user = user;
    }
}
