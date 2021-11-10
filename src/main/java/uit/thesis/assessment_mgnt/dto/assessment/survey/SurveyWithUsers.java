package uit.thesis.assessment_mgnt.dto.assessment.survey;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class SurveyWithUsers {
    private Long id;

    private int version;

    @JsonFormat(pattern = DateUtils.DATE_FORMAT)
    private LocalDateTime createAt;

    @JsonFormat(pattern = DateUtils.DATE_FORMAT)
    private LocalDateTime updateAt;

    private String createdBy;

    private String lastModifiedBy;

    private String code;

    private String name;

    private String scene;

    @JsonFormat(pattern = DateUtils.DATE_FORMAT)
    private LocalDateTime dueDate;

    private String contactPhone;

    private List<User> inspectors = new LinkedList<>();

    private User director;

    private User accountant;

    private User manager;
}
