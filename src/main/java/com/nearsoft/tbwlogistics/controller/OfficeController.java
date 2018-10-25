package com.nearsoft.tbwlogistics.controller;

import com.nearsoft.tbwlogistics.service.OfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OfficeController {

    private OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/offices")
    public ModelAndView getOffices(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        ModelAndView mav = new ModelAndView("office/offices");
        mav.addObject("officeList", officeService.findByName(name));
        return mav;
    }

    @GetMapping("/offices/{id}")
    public ModelAndView getOfficeById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("office/office");
        mav.addObject("office", officeService.findById(id));
        return mav;
    }
}
