package org.mycode.repository;

import org.mycode.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findEmployeeByFirstNameAndLastName(String firstName, String lastName);
}
