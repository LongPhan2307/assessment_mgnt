package uit.thesis.assessment_mgnt.model.human_resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "assessment_days_working")
@Getter
@Setter
public class DaysWorking extends AbstractEntity {

    @Column(unique = true)
    private String code;

    @Column(name = "work_day")
    private int workDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "employeeId")
    private Employee employee;
}
