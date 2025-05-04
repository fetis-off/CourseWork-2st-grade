package app.coursework.dto;

import lombok.Data;

@Data
public class EquipmentDto {
    private Long id;
    private String name;
    private Integer price;
    private Long parentId;
}
