package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.model.Rate;
import com.utn.PhoneLines.model.State;
import com.utn.PhoneLines.service.RateService;
import com.utn.PhoneLines.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/state")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    /*@GetMapping("/")
    public List<State> getAll()
    {
        return stateService.getAll();
    }


    @PostMapping("/")
    public void addState(@RequestBody final State state){
        stateService.add(state);

    }*/
}
