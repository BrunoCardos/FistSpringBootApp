package com.Sintaxy.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> list() {

        return ResponseEntity.ok(personService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> show(@PathVariable int id) {

        return ResponseEntity.ok(personService.show(id));
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.create(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody Person person) {
        try {
            Person savedPerson = personService.edit(id, person);
            return ResponseEntity.ok(savedPerson);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        try {
            personService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
