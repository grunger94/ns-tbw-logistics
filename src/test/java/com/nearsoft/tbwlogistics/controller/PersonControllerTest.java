package com.nearsoft.tbwlogistics.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllPersons() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/persons"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("personList", hasSize(6)))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                            hasProperty("id", Matchers.is(1L)),
                            hasProperty("name", Matchers.is("Rafael Alejandro Manrique Zamora")),
                            hasProperty("office", allOf(
                                hasProperty("id", Matchers.is(1L)),
                                hasProperty("name", Matchers.is("CUU")),
                                hasProperty("personList", hasSize(3)),
                                hasProperty("officeManagers", hasSize(1))
                            ))
                        )
                )));
    }

    @Test
    public void getPersonsByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons").param("name", "SOTO"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/persons"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("personList", hasSize(1)))
                .andExpect(model().attribute("personList",
                        hasItem(
                                allOf(
                                        hasProperty("id", Matchers.is(4L)),
                                        hasProperty("name", Matchers.is("Erika Soto")),
                                        hasProperty("office", allOf(
                                                hasProperty("id", Matchers.is(2L)),
                                                hasProperty("name", Matchers.is("HMO")),
                                                hasProperty("personList", hasSize(2)),
                                                hasProperty("officeManagers", hasSize(2))
                                        ))
                                )
                        )
                ));
    }

    @Test
    public void getPersonsByNotExistingName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons").param("name", "jack"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/persons"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("personList", hasSize(0)));
    }

    @Test
    public void getPersonById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons/{id}", 6))
                .andExpect(status().isOk())
                .andExpect(view().name("person/person"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("person",
                    allOf(
                            hasProperty("id", Matchers.is(6L)),
                            hasProperty("name", Matchers.is("Martha Urrea")),
                            hasProperty("office", allOf(
                                    hasProperty("id", Matchers.is(3L)),
                                    hasProperty("name", Matchers.is("CDMX")),
                                    hasProperty("personList", hasSize(1)),
                                    hasProperty("officeManagers", hasSize(1))
                            ))
                    )
                ));
    }

    @Test(expected = NestedServletException.class)
    public void getPersonByNotExistingId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons/{id}", 999));
    }

    @Test(expected = NestedServletException.class)
    public void getPersonByStringId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons/{id}", "999"));
    }

    @Test
    public void getPersonByInvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons/{id}", "some wild string"))
                .andExpect(status().is4xxClientError());
    }
}
