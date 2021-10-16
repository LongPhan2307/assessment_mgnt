package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "assessment_certificate")
public class Certificate extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String description;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "survey_id", referencedColumnName = "id")
//    @JsonIgnore
//    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    @JsonIgnore
    private Survey survey;

    @OneToOne(mappedBy = "certificate", cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private FileDB file;
}
