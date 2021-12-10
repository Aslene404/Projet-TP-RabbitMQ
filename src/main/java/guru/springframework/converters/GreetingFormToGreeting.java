package guru.springframework.converters;

import guru.springframework.commands.GreetingForm;
import guru.springframework.commands.EmployeeForm;
import guru.springframework.domain.Employee;
import guru.springframework.domain.Greeting;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class GreetingFormToGreeting implements Converter<GreetingForm, Greeting> {

    @Override
    public Greeting convert(GreetingForm greetingForm) {
        Greeting greeting = new Greeting();
        if (greetingForm.getId() != null  && !StringUtils.isEmpty(greetingForm.getId())) {
            greeting.setId(new Long(greetingForm.getId()));
        }
        
        greeting.setContent(greetingForm.getContent());
        
        return greeting;
    }

    
}
