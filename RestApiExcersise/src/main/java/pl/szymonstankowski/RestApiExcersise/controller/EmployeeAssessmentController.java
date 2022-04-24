package pl.szymonstankowski.RestApiExcersise.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szymonstankowski.RestApiExcersise.model.EmployeeAssessment;

@RestController
public class EmployeeAssessmentController {

    @PutMapping("/employee/{id}/assessments/{assessmentId}")
    public EmployeeAssessment updateAssessment(@PathVariable Long id, @PathVariable Long assessmentId){
        return null;
    }
}
