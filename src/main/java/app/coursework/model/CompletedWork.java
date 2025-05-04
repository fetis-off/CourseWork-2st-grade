package app.coursework.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "completed_work")
public class CompletedWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "execution_date")
    private LocalDate executionDate;

    @Column(name = "execution_time")
    private LocalTime executionTime;

    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;
}
