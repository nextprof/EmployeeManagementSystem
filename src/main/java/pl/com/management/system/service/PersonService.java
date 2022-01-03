package pl.com.management.system.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.management.system.dto.PersonProfitDto;
import pl.com.management.system.exception.PersonNotFoundException;
import pl.com.management.system.exception.PersonAlreadyExistsException;
import pl.com.management.system.model.Person;
import pl.com.management.system.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService {

    @Autowired
    private final PersonRepository personRepository;

    public Person getPersonByUid(final UUID personUuid) {
        return personRepository.findByPersonUuid(personUuid).orElseThrow(PersonNotFoundException::new);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        if(personRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new PersonAlreadyExistsException();
        }
        person.setPersonUuid(UUID.randomUUID());
        person.getAddress().setAddressUuid(UUID.randomUUID());
        person.setCreatedAt(LocalDate.now());
        person.setMoneyEarned(0.);
        person.setHoursWorked(0);
        person.setOnHolidays(false);
        personRepository.save(person);
    }

    public void editPerson(final UUID personUuid, final Person person) {

        final Person personFromDb = getPersonByUid(personUuid);

        personFromDb.setEmail(person.getEmail());
        personFromDb.setName(person.getName());
        personFromDb.setSurname(person.getSurname());
        personFromDb.setTelephone(person.getTelephone());

        personFromDb.getAddress().setCity(person.getAddress().getCity());
        personFromDb.getAddress().setStreet(person.getAddress().getStreet());
        personFromDb.getAddress().setStreetNumber(person.getAddress().getStreetNumber());
        personFromDb.getAddress().setHomeNumber(person.getAddress().getHomeNumber());

        personRepository.save(personFromDb);
    }

    public void deleteByUid(final UUID personUuid) {
        checkIsPersonExists(personUuid);
        final Person person = getPersonByUid(personUuid);
        personRepository.delete(person);
    }

    private void checkIsPersonExists(UUID personUuid) {
        if(personRepository.findByPersonUuid(personUuid).isEmpty()) {
            throw new PersonNotFoundException();
        }
    }

    public void addProfitPerson(UUID personUuid, PersonProfitDto personProfitDto) {
        checkIsPersonExists(personUuid);

        final Person person = getPersonByUid(personUuid);
        person.setHoursWorked(person.getHoursWorked() + personProfitDto.getHoursWorked());
        person.setMoneyEarned(person.getMoneyEarned() + personProfitDto.getMoneyEarned());
        person.setCreatedAt(LocalDate.now());
        personRepository.save(person);
    }

    public void toggleHolidays(UUID personUuid) {
        checkIsPersonExists(personUuid);
        final Person person = getPersonByUid(personUuid);
        person.setOnHolidays(!person.isOnHolidays());
        personRepository.save(person);
    }
}
