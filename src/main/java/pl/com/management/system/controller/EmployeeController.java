package pl.com.management.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.management.system.dto.EmployeeProfitDto;
import pl.com.management.system.mapper.EmployeeProfitDtoMapper;
import pl.com.management.system.model.Address;
import pl.com.management.system.model.Employee;
import pl.com.management.system.service.EmployeeService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("employees")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final EmployeeProfitDtoMapper employeeProfitDtoMapper;

    @GetMapping("/all")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "employees";
    }

    @GetMapping("info/{employeeUuid}")
    public String getSingleEmployee(@PathVariable UUID employeeUuid, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeByUid(employeeUuid));

        return "info";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        final Employee employee = Employee.builder().address(Address.builder().build()).build();
        model.addAttribute("employee", employee);
        model.addAttribute("action", "/employees/add");
        return "edit";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid Employee employee, BindingResult errors) {
        if (null != errors && errors.getErrorCount() > 0)
            return "edit";

        employeeService.save(employee);
        return "redirect:/employees/all";
    }
    @GetMapping("/profit/{employeeUuid}")
    public String changeProfitEmployee(@PathVariable UUID employeeUuid, Model model) {
        final Employee employee = employeeService.getEmployeeByUid(employeeUuid);
        model.addAttribute("employee", employee);
        model.addAttribute("action", "/employees/profit/" + employeeUuid);
        return "profit";
    }

    @PostMapping("/profit/{employeeUuid}")
    public String changeProfitEmployee(@PathVariable UUID employeeUuid, @Valid EmployeeProfitDto employeeProfitDto, BindingResult errors, Model model) {

        if (null != errors && errors.getErrorCount() > 0) {
            employeeProfitDto = employeeProfitDtoMapper.map(employeeService.getEmployeeByUid(employeeUuid));
            model.addAttribute("employee", employeeProfitDto);
            return "profit";
        } else {
            employeeService.addProfitEmployee(employeeUuid, employeeProfitDto);
            return "redirect:/employees/all";
        }
    }

    @GetMapping("/edit/{employeeUuid}")
    public String editEmployee(@PathVariable UUID employeeUuid, Model model) {
        final Employee employee = employeeService.getEmployeeByUid(employeeUuid);
        model.addAttribute("employee", employee);
        model.addAttribute("action", "/employees/edit/" + employeeUuid);
        return "edit";
    }

    @PostMapping("/edit/{employeeUuid}")
    public String editEmployee(@PathVariable UUID employeeUuid, @Valid Employee employee, BindingResult errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "edit";
        } else {
            employeeService.editEmployee(employeeUuid, employee);
            return "redirect:/employees/all";
        }
    }

    @PostMapping("/delete/{employeeUuid}")
    public String deleteEmployee(@PathVariable UUID employeeUuid) {
        employeeService.deleteByUid(employeeUuid);
        return "redirect:/employees/all";
    }

    @PostMapping("/holidays/{employeeUuid}")
    public String toggleHolidaysEmployee(@PathVariable UUID employeeUuid) {
        employeeService.toggleHolidays(employeeUuid);
        return "redirect:/employees/info/" + employeeUuid;
    }


}
