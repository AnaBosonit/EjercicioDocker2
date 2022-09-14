package com.example.EjercicioDocker2.person.application;



import com.example.EjercicioDocker2.person.domain.Person;
import com.example.EjercicioDocker2.person.infrastructure.controller.input.PersonInputDTO;
import com.example.EjercicioDocker2.person.infrastructure.controller.output.PersonOutputDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    public PersonOutputDTO addPerson(PersonInputDTO newPersonDto) throws Exception;

    public PersonOutputDTO findPersonById(int id) throws Exception;

    public List<PersonOutputDTO> findPersonByUsername(String Name) throws Exception;

    public List<PersonOutputDTO> getAll();

    public PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDto) throws Exception;

    public String deletePerson(int id) throws Exception;

    public Optional<Person> getPersonOptional(int id);
}