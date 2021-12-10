package guru.springframework.listener;

import guru.springframework.domain.Employee;
import guru.springframework.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class EmployeeMessageListener {

    private EmployeeRepository employeeRepository;

    private static final Logger log = LogManager.getLogger(EmployeeMessageListener.class);

    public EmployeeMessageListener(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link guru.springframework.SpringBootRabbitMQApplication} for more details
     * @param message
     */
    public void receiveMessage(Map<String, String> message) {
        log.info("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        String msg=message.get("message");
        log.info(msg);
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setMessageReceived(true);
        employee.setMessageCount(employee.getMessageCount() + 1);
        System.out.println(msg);

        employeeRepository.save(employee);
        log.info("Message processed...");
    }
}
