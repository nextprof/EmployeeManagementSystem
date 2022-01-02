package pl.com.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressDto {
    @NotBlank(message = "City should not be empty")
    String city;
    @NotBlank(message = "Street should not be empty")
    String street;
    @NotNull(message = "Street number cannot be empty")
    Integer streetNumber;
    @NotNull(message = "Home number cannot be empty")
    Integer homeNumber;
}
