package guru.springframework.converters;

import guru.springframework.commands.EmployeeForm;
import guru.springframework.commands.GreetingForm;
import guru.springframework.domain.Employee;
import guru.springframework.domain.Greeting;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class GreetingToGreetingForm implements Converter<Greeting, GreetingForm> {
    @Override
    public GreetingForm convert(Greeting greeting) {
        GreetingForm greetingForm = new GreetingForm();
        greetingForm.setId(greeting.getId());
        greetingForm.setContent(greeting.getContent());
        
        return greetingForm;
    }
}
