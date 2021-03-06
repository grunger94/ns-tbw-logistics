package com.nearsoft.tbwlogistics.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name="office_id", nullable=false)
    private Office office;

    protected Person() {}

    public Person(String name, Office office) {
        this.name = name;
        this.office = office;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Office getOffice() {
        return office;
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d, name='%s', office='%s']", id, name, office);
    }
}
