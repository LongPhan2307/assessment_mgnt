package uit.thesis.assessment_mgnt.model.assessment;

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

    @OneToOne(mappedBy = "certificate", cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY, optional = false)
    private Survey survey;

    @OneToOne(mappedBy = "certificate", cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY, optional = false)
    private FileDB file;
}