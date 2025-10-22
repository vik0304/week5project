package viktor.vasileski.week5project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import viktor.vasileski.week5project.entities.Trip;
import viktor.vasileski.week5project.exceptions.NotFoundException;
import viktor.vasileski.week5project.payloads.TripDTO;
import viktor.vasileski.week5project.repositories.TripRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public Page<Trip> findAll(int pageN){
        Pageable pageable = PageRequest.of(pageN, 25);
        return tripRepository.findAll(pageable);
    }

    public Trip findById(long id){
        return tripRepository.findById(id).orElseThrow(()->new NotFoundException("L'id inserito è errato o non esiste in db."));
    }

    public Trip saveTrip(TripDTO payload){
        Trip newTrip = new Trip(payload.destination(), payload.date(), payload.state());
        return tripRepository.save(newTrip);
    }

    public Trip findByIdAndUpdate(long id, TripDTO payload){
        Trip found = findById(id);
        found.setDestination(payload.destination());
        found.setDate(payload.date());
        found.setState(payload.state());
        return tripRepository.save(found);
    }

    public void findAndDelete(long id){
        Trip found = findById(id);
        tripRepository.delete(found);
    }
}
