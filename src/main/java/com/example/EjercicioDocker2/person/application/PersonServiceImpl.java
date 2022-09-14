package com.example.EjercicioDocker2.person.application;



import com.example.EjercicioDocker2.exceptions.NotFoundException;
import com.example.EjercicioDocker2.exceptions.UnprocessableEntityException;
import com.example.EjercicioDocker2.person.domain.Person;
import com.example.EjercicioDocker2.person.infrastructure.controller.input.PersonInputDTO;
import com.example.EjercicioDocker2.person.infrastructure.controller.output.PersonOutputDTO;
import com.example.EjercicioDocker2.person.infrastructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;


    @Override
    public PersonOutputDTO addPerson(PersonInputDTO newPersonDto) throws Exception {
        newPersonDto.setCreated_date(new Date());

        if(newPersonDto.getUser() == null)
            throw new UnprocessableEntityException("User field can not be null", 422);
        if(newPersonDto.getUser().length() > 10 || newPersonDto.getUser().length() < 6)
            throw new UnprocessableEntityException("User field length is not between 6 and 10", 422);
        if(newPersonDto.getPassword() == null)
            throw new UnprocessableEntityException("Password can not be null", 422);
        if(newPersonDto.getName() == null)
            throw new UnprocessableEntityException("Name can not be null", 422);
        if(newPersonDto.getCompany_email() == null)
            throw new UnprocessableEntityException("Company email is not null", 422);
        if(!newPersonDto.getCompany_email().contains("@"))
            throw new UnprocessableEntityException("Email format is not correct", 422);
        if(newPersonDto.getPersonal_email() == null)
            throw new UnprocessableEntityException("Company email is not null", 422);
        if(!newPersonDto.getPersonal_email().contains("@"))
            throw new UnprocessableEntityException("Email format is not correct", 422);
        if(newPersonDto.getCity() == null)
            throw new UnprocessableEntityException("City can not be null", 422);
        if(newPersonDto.getCreated_date() == null)
            throw new UnprocessableEntityException("Created date can not be null", 422);

        Person newPerson = new Person(newPersonDto);

        personRepository.save(newPerson);

        return new PersonOutputDTO(newPerson);
    }

    @Override
    public PersonOutputDTO findPersonById(int id) throws Exception {
        Optional<Person> personOptional = personRepository.findById(id);

        if(personOptional.isEmpty())
            throw new NotFoundException("Person does not exist", 404);

        return new PersonOutputDTO(personOptional.get());
    }

    @Override
    public List<PersonOutputDTO> findPersonByUsername(String username) throws Exception {
        List<Person> peopleList = personRepository.findByUsername(username);

        if(peopleList.isEmpty())
            throw new NotFoundException("Person does not exist", 404);


        return peopleList.stream().map(person -> new PersonOutputDTO(person)).collect(Collectors.toList());

    }

    @Override
    public List<PersonOutputDTO> getAll() {
        List<Person> peopleList = personRepository.findAll();

        List<PersonOutputDTO> peopleOutputDto = new ArrayList<>();

        /*for(Person person : peopleList)
            peopleOutputDto.add(new PersonOutputDto().transformPersonIntoPersonOutputDto(person));*/

        peopleOutputDto = peopleList.stream().map(person -> new PersonOutputDTO(person)).collect(Collectors.toList());

        return peopleOutputDto;
    }

    @Override
    public PersonOutputDTO updatePerson(int id, PersonInputDTO personInputDto) throws Exception {

        Optional<Person> personOpt = personRepository.findById(id);

        if(personOpt.isEmpty())
            throw new NotFoundException("The person does no exist", 404);
        if(personInputDto.getUser() == null)
            throw new UnprocessableEntityException("User field can not be null", 422);
        if(personInputDto.getUser().length() > 10 || personInputDto.getUser().length() < 6)
            throw new UnprocessableEntityException("User field length is not between 6 and 10", 422);
        if(personInputDto.getPassword() == null)
            throw new UnprocessableEntityException("Password can not be null", 422);
        if(personInputDto.getName() == null)
            throw new UnprocessableEntityException("Name can not be null", 422);
        if(personInputDto.getCompany_email() == null)
            throw new UnprocessableEntityException("Company email is not null", 422);
        if(!personInputDto.getCompany_email().contains("@"))
            throw new UnprocessableEntityException("Email format is not correct", 422);
        if(personInputDto.getPersonal_email() == null)
            throw new UnprocessableEntityException("Company email is not null", 422);
        if(!personInputDto.getPersonal_email().contains("@"))
            throw new UnprocessableEntityException("Email format is not correct", 422);
        if(personInputDto.getCity() == null)
            throw new UnprocessableEntityException("City can not be null", 422);

        Person person = personOpt.get();

        person.setUsername(personInputDto.getUser());
        person.setPassword(personInputDto.getPassword());
        person.setName(personInputDto.getName());
        person.setSurname(personInputDto.getSurname());
        person.setCompany_email(personInputDto.getCompany_email());
        person.setPersonal_email(personInputDto.getCompany_email());
        person.setCity(personInputDto.getCity());
        person.setActive(personInputDto.getActive());
        person.setImagen_url(personInputDto.getImagen_url());

        personRepository.save(person);

        return new PersonOutputDTO(person);
    }

    @Override
    public String deletePerson(int id) throws Exception {

        Optional<Person> personOpt = personRepository.findById(id);

        if(personOpt.isEmpty())
            throw new NotFoundException("The person does no exist", 404);

        personRepository.delete(personOpt.get());

        return "La persona ha sido borrada con éxito";
    }

    @Override
    public Optional<Person> getPersonOptional(int id) {
        return personRepository.findById(id);
    }
}