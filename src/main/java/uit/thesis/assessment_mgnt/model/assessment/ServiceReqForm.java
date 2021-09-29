package uit.thesis.assessment_mgnt.model.assessment;

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
@Table(name = "assessment_service_request_form")
public class ServiceReqForm extends AbstractEntity {
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    private Survey survey;

    @OneToMany(mappedBy = "serviceReqForm", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FileDB> files = new HashSet<>();

}
