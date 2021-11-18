package uit.thesis.assessment_mgnt.model.workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assessment_comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment extends AbstractEntity {
    @Lob
    private String content;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "username")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    @JsonIgnore
    private Survey survey;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private Set<Confirmation> confirmations = new HashSet<>();

    public Comment(String content, String title, User user, Survey survey){
        this.content = content;
        this.title = title;
        this.user = user;
        this.survey = survey;
    }

}
