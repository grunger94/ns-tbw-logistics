package com.nearsoft.tbwlogistics;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllPersons() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("persons"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("personList", hasSize(2)))
                .andExpect(model().attribute("personList", allOf(
                        hasItem(
                            allOf(
                                hasProperty("id", Matchers.is(1L)),
                                hasProperty("name", Matchers.is("Rafael Alejandro Manrique Zamora")),
                                hasProperty("office", Matchers.is("CUU"))
                            )
                        ),
                        hasItem(
                            allOf(
                                hasProperty("id", Matchers.is(2L)),
                                hasProperty("name", Matchers.is("Juan Daniel Amparán De La Garza")),
                                hasProperty("office", Matchers.is("CUU"))
                            )
                        )
                )));
    }

    @Test
    public void getPersonsByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/persons/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("person"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("person",
                        allOf(
                                hasProperty("id", Matchers.is(1L)),
                                hasProperty("name", Matchers.is("Rafael Alejandro Manrique Zamora")),
                                hasProperty("office", Matchers.is("CUU"))
                        )
                ));
    }
}
