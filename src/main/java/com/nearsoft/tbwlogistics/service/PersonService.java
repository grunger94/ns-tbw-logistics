package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.Person;
import com.nearsoft.tbwlogistics.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository repository) {
        personRepository = repository;
    }

    public List<Person> findByName(String name) {
        return personRepository.findByNameIgnoreCaseContaining(name);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
}
