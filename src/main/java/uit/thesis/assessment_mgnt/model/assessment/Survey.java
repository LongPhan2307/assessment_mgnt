package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "assessment_survey")
public class Survey extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String name;

    @Column(name = "status_form")
    @Enumerated(EnumType.STRING)
    private StatusForm statusForm;

    private String custNameVN;

    private String custNameIT;

    private String scene;

    private BigDecimal expenses;

    private String address;

    private String phoneCompany;

    private String taxCode;

    private String contactPhone;

    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private AssessmentCategory assessmentCategory;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Document> documents = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @OneToOne(mappedBy = "survey", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY, optional = false)
    private ServiceReqForm serviceReqForm;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SurveyPhase> phases = new ArrayList<>();
}