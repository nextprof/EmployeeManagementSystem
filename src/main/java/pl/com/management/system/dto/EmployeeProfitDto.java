package pl.com.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeProfitDto {
    @Min(value = 0,message = "money should be greater than 0")
    Double moneyEarned;
    @Min(value = 1,message = "hours should be greater than 0")
    Integer hoursWorked;
}
