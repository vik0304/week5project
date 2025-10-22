package viktor.vasileski.week5project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO(
        @NotBlank(message = "Obbligatorio inserire un username")
        String username,
        @NotBlank(message = "Obbligatorio inserire un nome")
        String name,
        @NotBlank(message = "Obbligatorio inserire un cognome")
        String surname,
        @NotBlank(message = "Obbligatorio inserire un email")
        @Email(message = "E' stato inserito un indirizzo email non valido")
        String email
) {
}
