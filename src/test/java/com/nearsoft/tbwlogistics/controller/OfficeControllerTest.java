package com.nearsoft.tbwlogistics.controller;

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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfficeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllOffices() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices"))
                .andExpect(status().isOk())
                .andExpect(view().name("office/offices"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("officeList", hasSize(1)))
                .andExpect(model().attribute("officeList", hasItem(
                        allOf(
                                hasProperty("id", Matchers.is(1L)),
                                hasProperty("name", Matchers.is("CUU")),
                                hasProperty("officeManagers", hasSize(1)),
                                hasProperty("personList", hasSize(2))
                        )
                )));
    }

    @Test
    public void getOfficesByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices").param("name", "CUU"))
                .andExpect(status().isOk())
                .andExpect(view().name("office/offices"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("officeList", hasSize(1)))
                .andExpect(model().attribute("officeList", hasItem(
                        allOf(
                                hasProperty("id", Matchers.is(1L)),
                                hasProperty("name", Matchers.is("CUU")),
                                hasProperty("officeManagers", hasSize(1)),
                                hasProperty("personList", hasSize(2))
                        )
                )));
    }

    @Test
    public void getOfficesByNotExistingName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices").param("name", "SLP"))
                .andExpect(status().isOk())
                .andExpect(view().name("office/offices"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("officeList", hasSize(0)));
    }

    @Test
    public void getOfficeById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("office/office"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("office",
                        allOf(
                                hasProperty("id", Matchers.is(1L)),
                                hasProperty("name", Matchers.is("CUU")),
                                hasProperty("personList", hasSize(2)),
                                hasProperty("officeManagers", hasSize(1))
                        )
                ));
    }

    @Test(expected = NestedServletException.class)
    public void getOfficeByNotExistingId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices/{id}", 999));
    }

    @Test(expected = NestedServletException.class)
    public void getOfficeByStringId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices/{id}", "999"));
    }

    @Test
    public void getOfficeByInvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/offices/{id}", "some wild string"))
                .andExpect(status().is4xxClientError());
    }
}
