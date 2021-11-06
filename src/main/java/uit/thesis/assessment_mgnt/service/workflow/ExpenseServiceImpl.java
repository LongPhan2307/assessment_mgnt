package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreateExpenseDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Expense;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.workflow.ExpenseRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl extends GenericServiceImpl<Expense, Long> implements ExpenseService {
    private ExpenseRepository expenseRepository;
    private ModelMapper modelMapper;
    private SurveyRepository surveyRepository;

    @Override
    public Expense addNewExpense(CreateExpenseDto dto) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        Expense expense = new Expense();
        expense = modelMapper.map(dto, Expense.class);
        expense.setSurvey(survey);
        return expenseRepository.save(expense);
    }
}
