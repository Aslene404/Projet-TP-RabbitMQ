package guru.springframework.repositories;

import guru.springframework.domain.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
