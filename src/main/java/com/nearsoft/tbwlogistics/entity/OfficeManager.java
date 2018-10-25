package com.nearsoft.tbwlogistics.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OfficeManager {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="office_id", nullable=false)
    private Office office;

    @OneToOne
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    protected OfficeManager() {
    }

    public OfficeManager(Office office, Person person) {
        this.office = office;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public Office getOffice() {
        return office;
    }

    public Person getPerson() {
        return person;
    }
}
