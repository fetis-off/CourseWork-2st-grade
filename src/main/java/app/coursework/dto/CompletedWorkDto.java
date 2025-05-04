package app.coursework.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class CompletedWorkDto {
    private Long id;
    private LocalDate executionDate;
    private LocalTime executionTime;
    private Long maintenanceId;
}