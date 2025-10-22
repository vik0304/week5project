package viktor.vasileski.week5project.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(
        String message,
        LocalDateTime date
) {
}
