package pl.szymonstankowski.RestApiExcersise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.szymonstankowski.RestApiExcersise.model.Employee;
import pl.szymonstankowski.RestApiExcersise.model.EmployeeDto;
import pl.szymonstankowski.RestApiExcersise.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    public static final Long NULL_ID = null;
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(new Employee(
                NULL_ID,
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.isActive(),
                employeeDto.getCreated()));
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(new Employee(
                id,
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.isActive(),
                employeeDto.getCreated()));
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmplpyee(id);
    }
}
