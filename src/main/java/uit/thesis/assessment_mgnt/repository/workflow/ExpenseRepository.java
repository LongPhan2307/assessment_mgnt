package uit.thesis.assessment_mgnt.repository.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.workflow.Expense;

import java.math.BigDecimal;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "" +
            "select sum(e.\"cost\") \n" +
            "from public.assessment_expense as e\n" +
            "where e.survey_code = ?1 "

    ,nativeQuery = true)
    BigDecimal ExpenseTotal(String surveyCode);
}
