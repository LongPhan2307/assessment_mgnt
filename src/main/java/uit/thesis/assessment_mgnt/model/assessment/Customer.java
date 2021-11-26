package uit.thesis.assessment_mgnt.model.assessment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uit.thesis.assessment_mgnt.common.AbstractEntity;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;
import uit.thesis.assessment_mgnt.model.workflow.Payment;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assessment_customer")
@Getter
@NoArgsConstructor
@Setter
public class Customer extends AbstractEntity {
    @Column(unique = true)
    private String code;

    private String custNameVN;

    private String custNameIT;

    private String address;

    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Survey> surveys = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Invoice> invoices = new HashSet<>();

    public Customer(String code, String custNameVN, String custNameIT, String address, String phone){
        this.code = code;
        this.custNameVN = custNameVN;
        this.custNameIT = custNameIT;
        this.address = address;
        this.phone = phone;
    }
}
