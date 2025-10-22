package viktor.vasileski.week5project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String destination;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private State state;

    public Trip(String destination, LocalDate date, State state){
        this.destination=destination;
        this.date=date;
        this.state=state;
    }
}
