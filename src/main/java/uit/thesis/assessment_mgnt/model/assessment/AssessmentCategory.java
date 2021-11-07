package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "assessment_category")
public class AssessmentCategory extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String name;

    private String description;

    @OneToMany(mappedBy = "assessmentCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Survey> surveys = new HashSet<>();

    public AssessmentCategory(String name){
        this.name = name;
        this.code = RandomStringUtils.randomAlphanumeric(10);
        this.description = "";
    }
}
