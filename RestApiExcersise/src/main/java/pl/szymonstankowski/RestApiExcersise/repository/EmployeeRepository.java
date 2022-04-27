package pl.szymonstankowski.RestApiExcersise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.szymonstankowski.RestApiExcersise.model.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {


        List<Employee> findAll();

        Optional<Employee> findById(Long id);

        @Modifying
        @Query(value = "update Employee e set e.isActive = false where e.id =?",
        nativeQuery = true)
        Employee deactivateEmployee(Long id);
}
