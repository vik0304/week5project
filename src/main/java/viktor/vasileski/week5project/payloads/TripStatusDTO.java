package viktor.vasileski.week5project.payloads;

import jakarta.validation.constraints.NotNull;
import viktor.vasileski.week5project.entities.State;


public record TripStatusDTO(
        @NotNull(message = "Obbligatorio inserire uno stato per il viaggio")
        State state
) {
}
