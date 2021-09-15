package uit.thesis.assessment_mgnt.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "assessment_company")
public class Company extends AbstractEntity {
    private String name;

    @Column(unique = true)
    private String code;


    @OneToMany(mappedBy = "headquater", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Company> companies = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Company headquater;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();

    private String address;
}
