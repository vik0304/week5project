package viktor.vasileski.week5project.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import viktor.vasileski.week5project.entities.State;

import java.time.LocalDate;

public record TripDTO(
        @NotBlank(message = "Obbligatorio inserire una destinazione")
        String destination,
        @NotNull(message = "Obbligatorio inserire una data di viaggio")
        @Future(message = "La data del viaggio non può essere oggi o nel passato")
        LocalDate date,
        @NotNull(message = "Obbligatorio inserire uno stato per il viaggio")
        State state
) {
}
