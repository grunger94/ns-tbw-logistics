package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.Person;
import com.nearsoft.tbwlogistics.repository.PersonRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    private PersonRepository personRepository;

    public PersonService(PersonRepository repository) {
        personRepository = repository;
    }

    public List<Person> findByName(String name) {
        List<Person> personList = new ArrayList<>();

        try {
            personList = personRepository.findByNameIgnoreCaseContaining(name);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.warning("personRepository.findByNameIgnoreCaseContaining() was called with null argument");
        }

        return personList;
    }

    public Person findById(Long id) {
        Person person = null;

        try {
            person = personRepository.findById(id).orElse(null);
        } catch (InvalidDataAccessApiUsageException e) {
            logger.warning("personRepository.findById() was called with null argument");
        }

        return person;
    }
}
