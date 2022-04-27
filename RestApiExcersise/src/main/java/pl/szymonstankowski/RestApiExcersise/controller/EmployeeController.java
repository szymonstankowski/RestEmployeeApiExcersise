package pl.szymonstankowski.RestApiExcersise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.szymonstankowski.RestApiExcersise.model.Employee;
import pl.szymonstankowski.RestApiExcersise.model.EmployeeDto;
import pl.szymonstankowski.RestApiExcersise.service.EmployeeService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    public static final Long NULL_ID = null;
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> getEmployees(){
        List<EntityModel<Employee>> employees = employeeService.getEmployees().stream()
                .map(employee -> EntityModel.of(employee))
                .toList();
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).getEmployees()).withSelfRel()
                );
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> getEmployee(@PathVariable Long id){
        return EntityModel.of(employeeService.getEmployee(id),
                WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees"),
                linkTo(EmployeeController.class).slash("employees").slash(id).slash("deactivate").withRel("deactivate")
                );
    }

    @PostMapping("/employees")
    public ResponseEntity<EntityModel<Employee>> createEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = employeeService.createEmployee(new Employee(
                NULL_ID,
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.isActive(),
                employeeDto.getCreated()
        ));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntityModel.of(employee,
                        WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).createEmployee(employeeDto)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees"),
                        linkTo(EmployeeController.class).slash("employees").slash(employee.getId()).slash("deactivate").withRel("deactivate"))
                );
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        employeeService.updateEmployee(new Employee(
                id,
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.isActive(),
                employeeDto.getCreated()
        ));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmplpyee(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employees{id}/deactivate")
    public void deactivateEmployee(@PathVariable Long id){
        employeeService.deactivateEmployee(id);
    }
}
