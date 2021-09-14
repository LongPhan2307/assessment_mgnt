package uit.thesis.assessment_mgnt.controller.system;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.system.CreateBranchDto;
import uit.thesis.assessment_mgnt.model.system.Branch;
import uit.thesis.assessment_mgnt.service.system.BranchService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/branch")
@AllArgsConstructor
public class BranchController {
    private BranchService service;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Branch> list = service.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse("There is no data", HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addBranch(@Valid @RequestBody CreateBranchDto branchDto,
                                            BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Branch branch = service.save(branchDto);
        return ResponseObject.getResponse(branch, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> addBranch(@Valid @RequestBody CreateBranchDto branchDto,
                                            @PathVariable("id") Long id,
                                            BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        if(id == null || id == 0)
            return ResponseObject.getResponse("id is not exists", HttpStatus.BAD_REQUEST);
        Branch branch = service.update(branchDto, id);
        if(branch == null)
            return ResponseObject.getResponse("Can not find Branch to Update", HttpStatus.BAD_REQUEST);
        return ResponseObject.getResponse(branch, HttpStatus.OK);

    }
}
