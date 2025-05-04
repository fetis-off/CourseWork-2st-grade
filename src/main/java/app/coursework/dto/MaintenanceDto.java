package app.coursework.dto;

import lombok.Data;

@Data
public class MaintenanceDto {
    private Long id;
    private String frequency;
    private Long equipmentId;
    private Long maintenanceEventId;
}
