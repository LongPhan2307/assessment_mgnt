package uit.thesis.assessment_mgnt.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.utils.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assessment_user")
@Getter
@Setter
public class User extends AbstractEntity {
    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    @NotNull
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    @JsonIgnore
    @NotNull
    private Role role;
}
