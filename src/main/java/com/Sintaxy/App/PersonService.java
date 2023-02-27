package com.Sintaxy.App;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private List<Person> people = new LinkedList<>();
    int id = 0;

    public Person create(Person person) {

        person.setId(id);
        this.people.add(person);
        id++;

        return person;
    }

    public Optional<Person> show(int id) {

        for (Person person : people) {

            if (person.getId() == id) {
                return Optional.of(person);
            }
        }

        return Optional.empty();
    }

    public void delete(int id) throws Exception {

        Optional<Person> optionalPerson = this.show(id);

        if (optionalPerson.isPresent()) {
            people.remove(optionalPerson.get());
            return;
        }
        throw new Exception("Person not found with id: " + id);
    }

    public List<Person> list() {

        return people;
    }

    public Person edit(int id, Person person) throws Exception {

        Optional<Person> optionalPerson = this.show(id);

        if (optionalPerson.isPresent()) {
            Person savedPerson = optionalPerson.get();

            savedPerson.setName(person.getName());
            savedPerson.setAge(person.getAge());
            return savedPerson;
        }
        throw new Exception("Person not found with id: " + id);

    }
}