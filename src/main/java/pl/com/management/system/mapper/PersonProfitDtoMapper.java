package pl.com.management.system.mapper;

import org.springframework.stereotype.Service;
import pl.com.management.system.dto.PersonProfitDto;
import pl.com.management.system.model.Person;

@Service
public class PersonProfitDtoMapper extends BaseMapper<PersonProfitDto , Person>{

    @Override
    protected PersonProfitDto _map(Person item) {
        return PersonProfitDto.builder()
                .hoursWorked(item.getHoursWorked())
                .moneyEarned(item.getMoneyEarned())
                .build();
    }
}
