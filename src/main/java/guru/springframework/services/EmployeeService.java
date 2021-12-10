package guru.springframework.services;

import guru.springframework.commands.EmployeeForm;
import guru.springframework.commands.GreetingForm;
import guru.springframework.domain.Employee;
import guru.springframework.domain.Greeting;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface EmployeeService {

    List<Employee> listAll();

    Employee getById(Long id);

    Employee saveOrUpdate(Employee employee);
    Greeting saveOrUpdate1(Greeting greeting);
    

    void delete(Long id);

    Employee saveOrUpdateEmployeeForm(EmployeeForm employeeForm);
    
    Greeting saveOrUpdateGreetingForm(GreetingForm greetingForm);

    void sendEmployeeMessage(String id,String xd);
}
