package com.nearsoft.tbwlogistics.controller;

import com.nearsoft.tbwlogistics.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService service) {
        personService = service;
    }

    @GetMapping("/persons")
    public ModelAndView getPersons(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        ModelAndView mav = new ModelAndView("person/persons");
        mav.addObject("personList", personService.findByName(name));
        return mav;
    }

    @GetMapping("/persons/{id}")
    public ModelAndView getPersonById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("person/person");
        mav.addObject("person", personService.findById(id));
        return mav;
    }
}
