package app.coursework.dto;

import lombok.Data;

@Data
public class NeedDto {
    private Long id;
    private Integer quantity;
    private Long materialId;
    private Long maintenanceId;
}
