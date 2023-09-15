package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class Controller {

    @GetMapping
    public String welcome() {
        return "Welcome to the GymBro API!";
    }

    @PostMapping("/calcWeight")
    public ArrayList<Float> getWeight(@RequestBody newLiftRequest lift) {
        return Calculator.goalProgressionTimeline(
            lift.getCurrentOneRepMax(),
            lift.getGoalOneRepMax(),
            lift.getUnits().toLowerCase(),
            lift.getProtocolType().toLowerCase()
        );
    }

}
