package uit.thesis.assessment_mgnt.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assessment_department")
@Getter
@Setter
public class Department extends AbstractEntity {
    @Column(unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Company company;
}
