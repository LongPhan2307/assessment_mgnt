package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uit.thesis.assessment_mgnt.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_files")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

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

    //    @CreatedBy
//    @Column(name = "created_by")
//    protected String createdBy;
//
//    @LastModifiedBy
//    @Column(name = "modified_by")
//    protected String lastModifiedBy;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @Column(name = "document_id", insertable = false, updatable = false)
    private Long documentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_certificate_id")
    @JsonIgnore
    private Certificate certificate;

    @Column(name = "service_req_form_id", insertable = false, updatable = false)
    private Long serviceReqFormId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_req_form_id")
//    private ServiceReqForm serviceReqForm;


    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public FileDB() {

    }
}
