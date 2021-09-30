package uit.thesis.assessment_mgnt.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.human_resource.Employee;
import uit.thesis.assessment_mgnt.utils.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "assessment_user_role_link"
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id")
    )
//    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,
                fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Employee employeeProfile;

    public User addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
        return this;
    }

}
