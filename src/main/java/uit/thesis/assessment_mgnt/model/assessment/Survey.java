package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Expense;
import uit.thesis.assessment_mgnt.model.workflow.Payment;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.utils.DateUtils;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private String scene;

    @JsonFormat(pattern = DateUtils.DATE_FORMAT)
    private LocalDateTime dueDate;

    private String contactPhone;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Customer customer;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Expense> expenses = new HashSet<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();

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
