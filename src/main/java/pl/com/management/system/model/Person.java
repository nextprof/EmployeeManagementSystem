package pl.com.management.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public final class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    UUID personUuid;
    @NotBlank(message = "Name should not be empty")
    String name;
    @NotBlank(message = "Surname should not be empty")
    String surname;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email is not valid")
    String email;
    @Pattern(regexp = "[0-9]{9}" , message = "Telephone should contain only 9 digits")
    String telephone;
    Double moneyEarned;
    Integer hoursWorked;
    LocalDate createdAt;

    boolean onHolidays;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Valid
    Address address;
}
