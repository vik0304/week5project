package viktor.vasileski.week5project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viktor.vasileski.week5project.entities.Reservation;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findById(UUID id);

    boolean existsByEmployeeIdAndRequestDate(long employeeId, LocalDate requestDate);
}
