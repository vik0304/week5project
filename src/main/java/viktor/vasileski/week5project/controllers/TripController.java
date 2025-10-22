package viktor.vasileski.week5project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viktor.vasileski.week5project.services.TripService;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;
}
