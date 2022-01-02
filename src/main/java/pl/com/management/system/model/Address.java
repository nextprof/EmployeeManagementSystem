package pl.com.management.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
@Entity
public final class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    UUID addressUuid;
    @NotBlank(message = "City should not be empty")
    String city;
    @NotBlank(message = "Street should not be empty")
    String street;
    @NotNull(message = "Street number cannot be empty")
    Integer streetNumber;
    @NotNull(message = "Home number cannot be empty")
    Integer homeNumber;
}
