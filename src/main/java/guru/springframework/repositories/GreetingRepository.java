package guru.springframework.repositories;

import guru.springframework.domain.Employee;
import guru.springframework.domain.Greeting;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 1/10/17.
 */
public interface GreetingRepository extends CrudRepository<Greeting, Long> {
}
