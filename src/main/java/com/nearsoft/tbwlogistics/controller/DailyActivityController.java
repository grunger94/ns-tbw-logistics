package com.nearsoft.tbwlogistics.controller;

import com.nearsoft.tbwlogistics.service.DailyActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DailyActivityController {

    private DailyActivityService dailyActivityService;

    public DailyActivityController(DailyActivityService dailyActivityService) {
        this.dailyActivityService = dailyActivityService;
    }

    @GetMapping("/daily-activities")
    public ModelAndView getDailyActivities(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        ModelAndView mav = new ModelAndView("daily-activity/daily-activities");
        mav.addObject("dailyActivities", dailyActivityService.findByName(name));
        return mav;
    }

    @GetMapping("/daily-activities/{id}")
    public ModelAndView getDailyActivityById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("daily-activity/daily-activity");
        mav.addObject("dailyActivity", dailyActivityService.findById(id));
        return mav;
    }
}
