package viktor.vasileski.week5project.payloads;

import jakarta.validation.constraints.Future;
import viktor.vasileski.week5project.entities.Employee;
import viktor.vasileski.week5project.entities.Trip;

import java.time.LocalDate;

public record ReservationDTO(
        @Future(message = "La data del viaggio non può essere oggi o nel passato")
        LocalDate requestDate,
        String preferences,
        long tripId,
        long employeeId
) {
}
