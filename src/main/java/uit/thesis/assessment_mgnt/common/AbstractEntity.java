package uit.thesis.assessment_mgnt.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uit.thesis.assessment_mgnt.utils.DateUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Version
    protected int version;

    @CreatedDate
    @Column(name = "created_at",nullable = false ,updatable = false)
    @JsonFormat(pattern = DateUtils.DATE_FORMAT)
    protected LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @JsonFormat(pattern = DateUtils.DATE_FORMAT)
    protected LocalDateTime updateAt;

    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    protected String lastModifiedBy;
}
