package uit.thesis.assessment_mgnt.model.human_resource;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assessment_days_working")
@Getter
@Setter
public class DaysWorking extends AbstractEntity {
    @Column(name = "work_day")
    private int workDay;
}
