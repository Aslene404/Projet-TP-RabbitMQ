package guru.springframework.services;

import guru.springframework.SpringBootRabbitMQApplication;
import guru.springframework.commands.EmployeeForm;
import guru.springframework.commands.GreetingForm;
import guru.springframework.converters.EmployeeFormToEmployee;
import guru.springframework.converters.GreetingFormToGreeting;
import guru.springframework.domain.Employee;
import guru.springframework.domain.Greeting;
import guru.springframework.repositories.EmployeeRepository;
import guru.springframework.repositories.GreetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.boot.autoconfigure.jms.JmsProperties.DeliveryMode;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;
    private GreetingRepository greetingRepository;
    private EmployeeFormToEmployee employeeFormToEmployee;
    private GreetingFormToGreeting greetingFormToGreeting;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeFormToEmployee employeeFormToEmployee,
                              RabbitTemplate rabbitTemplate) {
        this.employeeRepository = employeeRepository;
        this.employeeFormToEmployee = employeeFormToEmployee;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public List<Employee> listAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add); //fun with Java 8
        return employees;
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
    @Override
    public Greeting saveOrUpdate1(Greeting greeting) {
        greetingRepository.save(greeting);
        return greeting;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public Employee saveOrUpdateEmployeeForm(EmployeeForm employeeForm) {
        Employee savedEmployee = saveOrUpdate(employeeFormToEmployee.convert(employeeForm));

        System.out.println("Saved Employee Id: " + savedEmployee.getId());
        return savedEmployee;
    }
    @Override
    public Greeting saveOrUpdateGreetingForm(GreetingForm greetingForm) {
        Greeting savedEmployee = saveOrUpdate1(greetingFormToGreeting.convert(greetingForm));

        System.out.println("Saved Message: " + savedEmployee.getContent());
        return savedEmployee;
    }

    @Override
    public void sendEmployeeMessage(String id,String xd) {
        Employee employee = employeeRepository.findById(Long.valueOf(id)).orElse(null);
        String name = employee.getFullname();
        
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", id);
        Random rand = new Random(); //instance of random class
        int x = rand.nextInt(1000);
        
        actionmap.put("message", "Message pour "+name+" ayant comme identifiant "+id+" : "+xd);
        log.info("Sending the index request through queue message");
        
       
        rabbitTemplate.convertAndSend(SpringBootRabbitMQApplication.SFG_MESSAGE_QUEUE, actionmap ,m -> {
     m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
     return m;
 } );
        
        
    }
}
