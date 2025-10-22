package viktor.vasileski.week5project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viktor.vasileski.week5project.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
