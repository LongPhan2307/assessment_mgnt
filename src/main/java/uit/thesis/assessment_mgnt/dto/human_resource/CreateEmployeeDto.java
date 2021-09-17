package uit.thesis.assessment_mgnt.dto.human_resource;

import lombok.Getter;
import lombok.Setter;
import uit.thesis.assessment_mgnt.utils.EmployeeDegree;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateEmployeeDto {
    private String employeeId;

    private String fullname;

    private String address;

    private boolean gender;

    private String phone;

    private boolean marriage;

    private BigDecimal baseSalary;

    @Enumerated(EnumType.STRING)
    private EmployeeDegree degree;

    private String username;
}
