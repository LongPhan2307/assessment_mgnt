package uit.thesis.assessment_mgnt.controller.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.workflow.CreateExpenseDto;
import uit.thesis.assessment_mgnt.model.workflow.Expense;
import uit.thesis.assessment_mgnt.service.workflow.ExpenseService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.ExpenseDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Domain.API)
public class ExpenseController {
    private ExpenseService expenseService;

    @GetMapping(ExpenseDomain.EXPENSE_DOMAIN)
    public ResponseEntity<Object> findAll(){
        List<Expense> list = expenseService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(ExpenseDomain.EXPENSE_DOMAIN)
    public ResponseEntity<Object> addNewExpense(@RequestBody CreateExpenseDto dto,
                                                BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Expense expense = expenseService.addNewExpense(dto);
            return ResponseObject.getResponse(expense, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
