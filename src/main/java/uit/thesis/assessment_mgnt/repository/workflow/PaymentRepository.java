package uit.thesis.assessment_mgnt.repository.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.workflow.Payment;

import java.math.BigDecimal;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "" +
            "select p.debt\n" +
            "from public.assessment_payment as p\n" +
            "where p.customer_code = ?1\n" +
            "order by p.created_at desc\n" +
            "fetch first 1 rows only",
    nativeQuery = true)
    BigDecimal getRecentDebt(String customerCode);

    @Query(value = "select sum(p.amount_paid)\n" +
            "from public.assessment_payment as p\n" +
            "where p.customer_code = ?1 and p.survey_code = ?2"
    ,nativeQuery = true)
    BigDecimal getTotalPaid(String customerCode, String surveyCode);

}
