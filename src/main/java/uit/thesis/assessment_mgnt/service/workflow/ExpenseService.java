package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreateExpenseDto;
import uit.thesis.assessment_mgnt.model.workflow.Expense;

public interface ExpenseService extends GenericService<Expense, Long> {
    Expense addNewExpense(CreateExpenseDto dto) throws NotFoundException;
}
