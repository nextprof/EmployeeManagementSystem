package pl.com.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonProfitDto {
    @Min(value = 2,message = "test")
    Double moneyEarned;
    @Min(value = 2,message = "test")
    Integer hoursWorked;
}
