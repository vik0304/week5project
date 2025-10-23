package viktor.vasileski.week5project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import viktor.vasileski.week5project.entities.Reservation;
import viktor.vasileski.week5project.exceptions.ValidationException;
import viktor.vasileski.week5project.payloads.ReservationDTO;
import viktor.vasileski.week5project.services.ReservationService;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveReservation(@RequestBody @Validated ReservationDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()) throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        return reservationService.saveReservation(body);
    }

    @GetMapping
    public Page<Reservation> getAll(@RequestParam(defaultValue = "0") int pageN){
        return reservationService.findAll(pageN);
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable UUID id){
        return reservationService.findById(id);
    }

    @PutMapping("({id}")
    public Reservation updateReservation(@PathVariable UUID id, @RequestParam @Validated ReservationDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()) throw new ValidationException(validationResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        return reservationService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        reservationService.findByIdAndDelete(id);
    }
}
