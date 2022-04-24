package pl.szymonstankowski.RestApiExcersise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymonstankowski.RestApiExcersise.model.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {


        List<Employee> findAll();

        Optional<Employee> findById(Long id);
}
