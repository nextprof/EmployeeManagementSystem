package pl.com.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePersonDto {
    @NotBlank(message = "Name should not be empty")
    String name;
    @NotBlank(message = "Surname should not be empty")
    String surname;
    @NotNull
    @Email(message = "Email is not valid")
    String email;
    @Pattern(regexp = "[0-9]{9}" , message = "Telephone should contain only 9 digits")
    String telephone;
    @Valid
    CreateAddressDto address;
}
