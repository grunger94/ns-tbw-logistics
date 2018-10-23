package com.nearsoft.tbwlogistics.repository;

import com.nearsoft.tbwlogistics.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByNameIgnoreCaseContaining(String name);
}
