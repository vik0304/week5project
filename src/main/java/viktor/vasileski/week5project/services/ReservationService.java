package viktor.vasileski.week5project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viktor.vasileski.week5project.entities.Employee;
import viktor.vasileski.week5project.entities.Reservation;
import viktor.vasileski.week5project.entities.Trip;
import viktor.vasileski.week5project.exceptions.BadRequestException;
import viktor.vasileski.week5project.exceptions.NotFoundException;
import viktor.vasileski.week5project.payloads.ReservationDTO;
import viktor.vasileski.week5project.repositories.ReservationRepository;

import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TripService tripService;

    public Page<Reservation> findAll(int pageN){
        Pageable pageable = PageRequest.of(pageN, 25);
        return reservationRepository.findAll(pageable);
    }

    public Reservation findById(UUID id){
        return reservationRepository.findById(id).orElseThrow(()->new NotFoundException("L'id inserito è errato o non esiste in db."));
    }

    public Reservation saveReservation(ReservationDTO payload){
        Trip foundTrip = tripService.findById(payload.tripId());
        Employee foundEmployee = employeeService.findById(payload.employeeId());
        if(reservationRepository.existsByEmployeeIdAndRequestDate(payload.employeeId(), payload.requestDate())){
            throw new BadRequestException("E' già presente una prenotazione per questa data");
        }
        Reservation newReservation = new Reservation(payload.requestDate(), payload.preferences(), foundTrip, foundEmployee);
        return reservationRepository.save(newReservation);
    }


}
