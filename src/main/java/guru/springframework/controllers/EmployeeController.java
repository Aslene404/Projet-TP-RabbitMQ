package guru.springframework.controllers;

import guru.springframework.commands.EmployeeForm;
import guru.springframework.commands.GreetingForm;
import guru.springframework.converters.EmployeeToEmployeeForm;
import guru.springframework.domain.Employee;
import guru.springframework.domain.Greeting;
import guru.springframework.services.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class EmployeeController {

    private static final Logger log = LogManager.getLogger(EmployeeController.class);

    private EmployeeService employeeService;

    private EmployeeToEmployeeForm employeeToEmployeeForm;



    @Autowired
    public void setEmployeeToEmployeeForm(EmployeeToEmployeeForm employeeToEmployeeForm) {
        this.employeeToEmployeeForm = employeeToEmployeeForm;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/employee/list";
    }

    @RequestMapping({"/employee/list", "/employee"})
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.listAll());
        return "employee/list";
    }

    @RequestMapping("/employee/show/{id}")
    public String getEmployee(@PathVariable String id, Model model){
        model.addAttribute("employee", employeeService.getById(Long.valueOf(id)));
        return "employee/show";
    }

    @RequestMapping("employee/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Employee employee = employeeService.getById(Long.valueOf(id));
        EmployeeForm employeeForm = employeeToEmployeeForm.convert(employee);

        model.addAttribute("employeeForm", employeeForm);
        return "employee/employeeform";
    }

    @RequestMapping("/employee/new")
    public String newEmployee(Model model){
        model.addAttribute("employeeForm", new EmployeeForm());
        return "employee/employeeform";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@Valid EmployeeForm employeeForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "employee/employeeform";
        }

        Employee savedEmployee = employeeService.saveOrUpdateEmployeeForm(employeeForm);

        return "redirect:/employee";
    }

    @RequestMapping("/employee/delete/{id}")
    public String delete(@PathVariable String id){
        employeeService.delete(Long.valueOf(id));
        return "redirect:/employee/list";
    }
    
    @RequestMapping(value = "/employee/indexEmployee/{id}", method = RequestMethod.GET) 
    public String indexEmployee(@PathVariable String id,@Valid GreetingForm employeeForm,@RequestParam("content") String content){
        

        
        employeeService.sendEmployeeMessage(id,content);
        return "redirect:/employee/show/"+id;
    }
}
