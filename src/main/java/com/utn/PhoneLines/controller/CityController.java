package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }



}
