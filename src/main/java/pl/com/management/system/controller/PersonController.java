package pl.com.management.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.management.system.dto.CreateAddressDto;
import pl.com.management.system.dto.CreatePersonDto;
import pl.com.management.system.mapper.CreateAddressDtoMapper;
import pl.com.management.system.mapper.CreatePersonDtoMapper;
import pl.com.management.system.model.Address;
import pl.com.management.system.model.Person;
import pl.com.management.system.service.PersonService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {

    @Autowired
    private final PersonService personService;

    @Autowired
    private final CreatePersonDtoMapper createPersonDtoMapper;

    @GetMapping("/list")
    public String getPersons(Model model) {
        model.addAttribute("persons", personService.getPersons());
        return "list";
    }

    @GetMapping("details/{personUuid}")
    public String getPerson(@PathVariable UUID personUuid, Model model) {
        model.addAttribute("person", personService.getPersonByUid(personUuid));

        return "details";
    }

    @GetMapping("/add")
    public String addPerson(Model model) {
        final Person person = Person.builder().address(Address.builder().build()).build();
        model.addAttribute("person", person);
        model.addAttribute("action", "/person/add");
        return "edit";
    }

    @PostMapping("/add")
    public String addPerson(@Valid Person person, BindingResult errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "edit";
        } else {
           // Person person = createPersonDtoMapper.map(person);
            person.setCreatedAt(LocalDate.now());
            person.setMoneyEarned(0.);
            person.setHoursWorked(0);
            personService.save(person);
            return "redirect:/person/list";
        }
    }
    @GetMapping("/profit/{personUuid}")
    public String changeProfitPerson(@PathVariable UUID personUuid, Model model) {
        final Person person = personService.getPersonByUid(personUuid);
        model.addAttribute("person", person);
        model.addAttribute("action", "/person/profit/" + personUuid);
        return "profit";
    }

    @PostMapping("/profit/{personUuid}")
    public String changeProfitPerson(@PathVariable UUID personUuid,@Valid Person person) {
        personService.addProfitPerson(personUuid, person);
        return "redirect:/person/list";
    }

    @GetMapping("/edit/{personUuid}")
    public String editPerson(@PathVariable UUID personUuid, Model model) {
        final Person person = personService.getPersonByUid(personUuid);
        model.addAttribute("person", person);
        model.addAttribute("action", "/person/edit/" + personUuid);
        return "edit";
    }


    @PostMapping("/edit/{personUuid}")
    public String editPerson(@PathVariable UUID personUuid, Person person) {
        personService.editPerson(personUuid, person);
        return "redirect:/person/list";
    }

    @PostMapping("/delete/{personUuid}")
    public String deletePerson(@PathVariable UUID personUuid) {
        personService.deleteByUid(personUuid);
        return "redirect:/person/list";
    }
















}
