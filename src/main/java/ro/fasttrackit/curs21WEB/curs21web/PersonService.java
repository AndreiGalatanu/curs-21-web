package ro.fasttrackit.curs21WEB.curs21web;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs21WEB.curs21web.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();

    public PersonService() {
        persons.addAll(new PersonReader().readPersons());
    }

    public List<Person> getAllPersons(String name) {
        if (name == null) {
            return persons;
        } else {
            return persons.stream()
                    .filter(person -> person.getName()
                            .equals(name))
                    .collect(toList());
        }
    }

    public Optional<Person> getPerson(int id) {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }


}
