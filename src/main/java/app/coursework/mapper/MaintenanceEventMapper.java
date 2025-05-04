package app.coursework.mapper;

import app.coursework.dto.MaintenanceEventDto;
import app.coursework.model.MaintenanceEvent;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceEventMapper {

    public MaintenanceEventDto toDto(MaintenanceEvent entity) {
        if (entity == null) {
            return null;
        }

        MaintenanceEventDto dto = new MaintenanceEventDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProcedureDescription(entity.getProcedureDescription());
        return dto;
    }

    public MaintenanceEvent toEntity(MaintenanceEventDto dto) {
        if (dto == null) {
            return null;
        }

        MaintenanceEvent entity = new MaintenanceEvent();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setProcedureDescription(dto.getProcedureDescription());
        return entity;
    }
}

