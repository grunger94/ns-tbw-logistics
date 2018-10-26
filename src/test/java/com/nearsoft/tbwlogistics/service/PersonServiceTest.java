package com.nearsoft.tbwlogistics.service;

import com.nearsoft.tbwlogistics.entity.Person;
import com.nearsoft.tbwlogistics.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepositoryMock;

    @InjectMocks
    private PersonService personServiceWithMockedRepo;

    @Autowired
    private PersonService personService;

    @Test
    public void findByIdCallsRepositoryMethod() {
        personServiceWithMockedRepo.findById(1L);
        Mockito.verify(personRepositoryMock, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findByNotExistingId() {
        Person person = personService.findById(999L);
        Assert.assertNull(person);
    }

    @Test
    public void findByNullId() {
        Person person = personService.findById(null);
        Assert.assertNull(person);
    }

    @Test
    public void findByNameCallsRepositoryMethod() {
        personServiceWithMockedRepo.findByName("Rafa");
        Mockito.verify(personRepositoryMock, Mockito.times(1)).findByNameIgnoreCaseContaining("Rafa");
    }

    @Test
    public void findByNotExistingName() {
        List<Person> persons = personService.findByName("Not a nearsoftian");
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.isEmpty());
    }

    @Test
    public void findByNullName() {
        List<Person> persons = personService.findByName(null);
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.isEmpty());
    }

    @Test
    public void findByEmptyName() {
        List<Person> persons = personService.findByName("");
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
        Assert.assertEquals(persons.size(), 2);
    }
}
