package pl.com.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.management.system.model.Person;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPersonUuid(final UUID personUuid);

    Optional<Person> findByEmail(final String email);
}
