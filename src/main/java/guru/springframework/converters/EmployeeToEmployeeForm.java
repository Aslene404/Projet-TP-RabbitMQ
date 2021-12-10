package guru.springframework.converters;

import guru.springframework.commands.EmployeeForm;
import guru.springframework.domain.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class EmployeeToEmployeeForm implements Converter<Employee, EmployeeForm> {
    @Override
    public EmployeeForm convert(Employee employee) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(employee.getId());
        employeeForm.setFullname(employee.getFullname());
        employeeForm.setSalary(employee.getSalary());
        employeeForm.setDepartement(employee.getDepartement());
        employeeForm.setRank(employee.getRank());
        return employeeForm;
    }
}
