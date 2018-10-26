package com.nearsoft.tbwlogistics.repository;

import com.nearsoft.tbwlogistics.entity.Office;
import com.nearsoft.tbwlogistics.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findByName() {
        List<Person> personList = personRepository.findByNameIgnoreCaseContaining("Rafael");

        Assert.assertNotNull(personList);
        Assert.assertFalse(personList.isEmpty());
        Assert.assertEquals(personList.size(), 1);

        Person person = personList.get(0);

        Assert.assertEquals(person.getId(), new Long(1));
        Assert.assertEquals(person.getName(), "Rafael Alejandro Manrique Zamora");
        Assert.assertNotNull(person.getOffice());

        Office office = person.getOffice();
        Assert.assertEquals(office.getId(), new Long(1));
        Assert.assertEquals(office.getName(), "CUU");
        Assert.assertNotNull(office.getPersonList());
        Assert.assertEquals(office.getPersonList().size(), 2);
        Assert.assertNotNull(office.getOfficeManagers());
        Assert.assertEquals(office.getOfficeManagers().size(), 1);
    }

    @Test
    public void findByNotExistingName() {
        List<Person> personList = personRepository.findByNameIgnoreCaseContaining("Not a nearsoftian");

        Assert.assertNotNull(personList);
        Assert.assertTrue(personList.isEmpty());
    }

    @Test
    public void findByEmptyName() {
        List<Person> personList = personRepository.findByNameIgnoreCaseContaining("");

        Assert.assertNotNull(personList);
        Assert.assertFalse(personList.isEmpty());
        Assert.assertEquals(personList.size(), 2);

        Person person = personList.get(0);

        Assert.assertEquals(person.getId(), new Long(1));
        Assert.assertEquals(person.getName(), "Rafael Alejandro Manrique Zamora");
        Assert.assertNotNull(person.getOffice());

        person = personList.get(1);

        Assert.assertEquals(person.getId(), new Long(2));
        Assert.assertEquals(person.getName(), "Juan Daniel Amparán De La Garza");
        Assert.assertNotNull(person.getOffice());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByNullName() {
        personRepository.findByNameIgnoreCaseContaining(null);
    }

    @Test
    public void findById() {
        Optional<Person> optionalPerson = personRepository.findById(1L);
        Assert.assertNotNull(optionalPerson);
        Assert.assertTrue(optionalPerson.isPresent());

        Person person = optionalPerson.get();
        Assert.assertNotNull(person);
        Assert.assertEquals(person.getId(), new Long(1));
        Assert.assertEquals(person.getName(), "Rafael Alejandro Manrique Zamora");
        Assert.assertNotNull(person.getOffice());
    }

    @Test
    public void findByNotExistingId() {
        Optional<Person> optionalPerson = personRepository.findById(999L);
        Assert.assertNotNull(optionalPerson);
        Assert.assertFalse(optionalPerson.isPresent());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findByNullId() {
        personRepository.findById(null);
    }
}
