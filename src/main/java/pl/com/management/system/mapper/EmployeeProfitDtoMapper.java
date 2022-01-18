package pl.com.management.system.mapper;

import org.springframework.stereotype.Service;
import pl.com.management.system.dto.EmployeeProfitDto;
import pl.com.management.system.model.Employee;

@Service
public class EmployeeProfitDtoMapper extends BaseMapper<EmployeeProfitDto, Employee>{

    @Override
    protected EmployeeProfitDto _map(Employee item) {
        return EmployeeProfitDto.builder()
                .hoursWorked(item.getHoursWorked())
                .moneyEarned(item.getMoneyEarned())
                .build();
    }
}
