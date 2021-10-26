package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
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
    @JsonIgnore
    private AssessmentCategory assessmentCategory;
//
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Document> documents = new HashSet<>();
//
    @OneToOne(mappedBy = "survey"
            )
    private Certificate certificate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "assessment_survey_inspector_link"
            , joinColumns = @JoinColumn(name = "survey_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> inspectors = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "username")
    private User director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "username")
    private User accountant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "username")
    private User manager;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

//    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
//    private Set<Certificate> certificates = new HashSet<>();
//
//    @OneToOne(mappedBy = "survey", cascade = CascadeType.ALL
//            , fetch = FetchType.LAZY, optional = false)
//    @JsonIgnore
//    private ServiceReqForm serviceReqForm;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(referencedColumnName = "name")
//    @JsonIgnore
//    private Workflow workflow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name")
    private Phase phase;

    public Survey addInspector(User user){
        this.getInspectors().add(user);
        user.getSurveys().add(this);
        return this;
    }

    public Survey removeInspector(User user){
        this.getInspectors().remove(user);
        user.getSurveys().remove(this);
        return this;
    }
}
