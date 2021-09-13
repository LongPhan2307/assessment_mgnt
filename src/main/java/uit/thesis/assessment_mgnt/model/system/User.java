package uit.thesis.assessment_mgnt.model.system;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.utils.UserStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "assessment_user")
@Getter
@Setter
public class User extends AbstractEntity {
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
