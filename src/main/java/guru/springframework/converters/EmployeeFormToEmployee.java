package guru.springframework.converters;

import guru.springframework.commands.EmployeeForm;
import guru.springframework.domain.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class EmployeeFormToEmployee implements Converter<EmployeeForm, Employee> {

    @Override
    public Employee convert(EmployeeForm employeeForm) {
        Employee employee = new Employee();
        if (employeeForm.getId() != null  && !StringUtils.isEmpty(employeeForm.getId())) {
            employee.setId(new Long(employeeForm.getId()));
        }
        employee.setFullname(employeeForm.getFullname());
        employee.setSalary(employeeForm.getSalary());
        employee.setDepartement(employeeForm.getDepartement());
        employee.setRank(employeeForm.getRank());
        return employee;
    }
}
