package pl.szymonstankowski.RestApiExcersise.service;

import org.springframework.stereotype.Service;
import pl.szymonstankowski.RestApiExcersise.model.Employee;
import pl.szymonstankowski.RestApiExcersise.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;

    public List<Employee>getEmployees(){
        return employeeRepository.findAll();
    }
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmplpyee(Long id) {
        employeeRepository.deleteById(id);
    }
}
