package com.nearsoft.tbwlogistics.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "office", fetch = FetchType.EAGER)
    private List<Person> personList;

    @OneToMany(mappedBy = "office", fetch = FetchType.EAGER)
    private List<OfficeManager> officeManagers;

    protected Office() {}

    public Office(String name, List<OfficeManager> officeManagers, List<Person> personList) {
        this.name = name;
        this.officeManagers = officeManagers;
        this.personList = personList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<OfficeManager> getOfficeManagers() {
        return officeManagers;
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d, name='%s', officeManagers='%s']", id, name, officeManagers);
    }
}
