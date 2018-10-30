package com.nearsoft.tbwlogistics.controller;

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
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DailyActivityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllDailyActivities() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities"))
                .andExpect(status().isOk())
                .andExpect(view().name("daily-activity/daily-activities"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("dailyActivities", hasSize(2)))
                .andExpect(model().attribute("dailyActivities", notNullValue()));
    }

    @Test
    public void getDailyActivitiesByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities").param("name", "carnita"))
                .andExpect(status().isOk())
                .andExpect(view().name("daily-activity/daily-activities"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("dailyActivities", hasSize(1)))
                .andExpect(model().attribute("dailyActivities", notNullValue()));
    }

    @Test
    public void getDailyActivitiesByNotExistingName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities").param("name", "actividad sana"))
                .andExpect(status().isOk())
                .andExpect(view().name("daily-activity/daily-activities"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("dailyActivities", hasSize(0)));
    }

    @Test
    public void getDailyActivityById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("daily-activity/daily-activity"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attribute("dailyActivity",
                        allOf(
                                hasProperty("id"),
                                hasProperty("name"),
                                hasProperty("description"),
                                hasProperty("date"),
                                hasProperty("durationInHours"),
                                hasProperty("place"),
                                hasProperty("attendanceLimit")
                        )
                ));
    }

    @Test(expected = NestedServletException.class)
    public void getDailyActivityByNotExistingId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities/{id}", 999));
    }

    @Test(expected = NestedServletException.class)
    public void getDailyActivityByStringId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities/{id}", "999"));
    }

    @Test
    public void getDailyActivityByInvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/daily-activities/{id}", "some wild string"))
                .andExpect(status().is4xxClientError());
    }
}
