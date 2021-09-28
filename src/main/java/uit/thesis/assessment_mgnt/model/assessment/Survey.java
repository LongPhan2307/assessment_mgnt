package uit.thesis.assessment_mgnt.model.assessment;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "assessment_survey")
public class Survey extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String nameVN;

    private String nameIT;

    private String scene;

    private BigDecimal expenses;

    private String addressScene;

    private String phone;

    @Column(unique = true)
    private String taxCode;

    private String contactPhone;
}
