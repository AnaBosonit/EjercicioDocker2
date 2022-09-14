package com.example.EjercicioDocker2.person.infrastructure.controller;

import com.example.EjercicioDocker2.person.application.PersonServiceImpl;
import com.example.EjercicioDocker2.person.infrastructure.controller.input.PersonInputDTO;
import com.example.EjercicioDocker2.person.infrastructure.controller.output.PersonOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping("/person")
    public PersonOutputDTO addPerson(@RequestBody PersonInputDTO personInputDTO) throws Exception {
        return personService.addPerson(personInputDTO);
    }

    @DeleteMapping("person/{id}")
    public String deletePerson(@PathVariable int id) throws Exception {
        return personService.deletePerson(id);
    }


    @GetMapping("/person/all")
    public List<PersonOutputDTO> getAll(){
        return personService.getAll();
    }

    @GetMapping("/person/{id}")
    public PersonOutputDTO getPersonById(@PathVariable int id) throws Exception {
        return personService.findPersonById(id);
    }

    @GetMapping("/person/username/{username}")
    public List<PersonOutputDTO> getPersonByUsername(@PathVariable String username) throws Exception {
        return personService.findPersonByUsername(username);
    }

    @PutMapping("/person/{id}")
    public PersonOutputDTO updatePerson(@PathVariable int id, @RequestBody PersonInputDTO personInputDTO) throws Exception {
        return personService.updatePerson(id, personInputDTO);
    }
}