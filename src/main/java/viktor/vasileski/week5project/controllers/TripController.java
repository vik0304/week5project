package viktor.vasileski.week5project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viktor.vasileski.week5project.entities.Trip;
import viktor.vasileski.week5project.exceptions.ValidationException;
import viktor.vasileski.week5project.payloads.TripDTO;
import viktor.vasileski.week5project.services.TripService;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip saveTrip(@RequestBody @Validated TripDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }return tripService.saveTrip(body);
    }

    @GetMapping
    public Page<Trip> getAll(@RequestParam(defaultValue = "0") int pageN){
        return tripService.findAll(pageN);
    }

    @GetMapping("/{id}")
    public Trip getById(@PathVariable long id){
        return tripService.findById(id);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable long id, @RequestBody @Validated TripDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        } return tripService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        tripService.findAndDelete(id);
    }
}
