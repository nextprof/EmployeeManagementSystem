package pl.com.management.system.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.management.system.dto.EmployeeProfitDto;
import pl.com.management.system.exception.AppException;
import pl.com.management.system.model.Employee;
import pl.com.management.system.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public Employee getEmployeeByUid(final UUID employeeUuid) {
        Optional<Employee> employee = employeeRepository.findByEmployeeUuid(employeeUuid);
        if(employee.isEmpty())
            throw new AppException("Employee not found");
        return employee.get();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        if(employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new AppException("Employee already exists");
        }
        employee.setEmployeeUuid(UUID.randomUUID());
        employee.getAddress().setAddressUuid(UUID.randomUUID());
        employee.setCreatedAt(LocalDate.now());
        employee.setMoneyEarned(0.);
        employee.setHoursWorked(0);
        employee.setOnHolidays(false);
        employeeRepository.save(employee);
    }

    public void editEmployee(final UUID employeeUuid, final Employee employee) {

        final Employee employeeFromDb = getEmployeeByUid(employeeUuid);

        employeeFromDb.setEmail(employee.getEmail());
        employeeFromDb.setName(employee.getName());
        employeeFromDb.setSurname(employee.getSurname());
        employeeFromDb.setTelephone(employee.getTelephone());

        employeeFromDb.getAddress().setCity(employee.getAddress().getCity());
        employeeFromDb.getAddress().setStreet(employee.getAddress().getStreet());
        employeeFromDb.getAddress().setStreetNumber(employee.getAddress().getStreetNumber());
        employeeFromDb.getAddress().setHomeNumber(employee.getAddress().getHomeNumber());

        employeeRepository.save(employeeFromDb);
    }

    public void deleteByUid(final UUID employeeUuid) {
        checkIsEmployeeExists(employeeUuid);
        final Employee employee = getEmployeeByUid(employeeUuid);
        employeeRepository.delete(employee);
    }

    private void checkIsEmployeeExists(UUID employeeUuid) {
        if(employeeRepository.findByEmployeeUuid(employeeUuid).isEmpty()) {
            throw new AppException("Employee not found");
        }
    }

    public void addProfitEmployee(UUID employeeUuid, EmployeeProfitDto employeeProfitDto) {
        checkIsEmployeeExists(employeeUuid);

        final Employee employee = getEmployeeByUid(employeeUuid);
        employee.setHoursWorked(employee.getHoursWorked() + employeeProfitDto.getHoursWorked());
        employee.setMoneyEarned(employee.getMoneyEarned() + employeeProfitDto.getMoneyEarned());
        employee.setCreatedAt(LocalDate.now());
        employeeRepository.save(employee);
    }

    public void toggleHolidays(UUID employeeUuid) {
        checkIsEmployeeExists(employeeUuid);
        final Employee employee = getEmployeeByUid(employeeUuid);
        employee.setOnHolidays(!employee.isOnHolidays());
        employeeRepository.save(employee);
    }
}
