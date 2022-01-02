package pl.com.management.system.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.management.system.dto.CreatePersonDto;
import pl.com.management.system.model.Person;

@Service
public class CreatePersonDtoMapper extends BaseMapper<Person, CreatePersonDto> {

    @Autowired
    private CreateAddressDtoMapper createAddressDtoMapper;

    @Override
    protected Person _map(CreatePersonDto item) {
        return Person.builder()
                .name(item.getName())
                .surname(item.getSurname())
                .email(item.getEmail())
                .telephone(item.getTelephone())
                .address(createAddressDtoMapper.map(item.getAddress()))
                .build();
    }
}
