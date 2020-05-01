package ro.fasttrackit.curs21WEB.curs21web;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs21WEB.curs21web.domain.Person;

import java.util.List;

@RestController
@RequestMapping("persons")

public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    List<Person> getAllPersons(@RequestParam(required = false) String name) {
        return service.getAllPersons(name);
    }

    @GetMapping("{id}")
    Person getPerson(@PathVariable int id) {
        return service.getPerson(id)
                .orElseThrow(() -> new RuntimeException("Person " + id + " not found"));
    }

}
