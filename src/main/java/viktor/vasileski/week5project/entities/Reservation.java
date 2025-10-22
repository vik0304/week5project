package viktor.vasileski.week5project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "request_date")
    private LocalDate requestDate;
    private String preferences;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Reservation(LocalDate requestDate, String preferences, Trip trip, Employee employee){
        this.requestDate=requestDate;
        this.preferences=preferences;
        this.trip=trip;
        this.employee=employee;
    }
}
