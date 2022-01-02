package pl.com.management.system.mapper;

import org.springframework.stereotype.Service;
import pl.com.management.system.dto.CreateAddressDto;
import pl.com.management.system.model.Address;

@Service
public class CreateAddressDtoMapper extends BaseMapper<Address, CreateAddressDto> {

    @Override
    protected Address _map(CreateAddressDto item) {
        return Address.builder()
                .city(item.getCity())
                .street(item.getStreet())
                .streetNumber(item.getStreetNumber())
                .homeNumber(item.getHomeNumber())
                .build();
    }
}
