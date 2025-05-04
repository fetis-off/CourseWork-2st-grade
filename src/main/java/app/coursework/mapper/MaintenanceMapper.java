package app.coursework.mapper;

import app.coursework.dto.MaintenanceDto;
import app.coursework.model.Maintenance;
import app.coursework.repository.EquipmentRepository;
import app.coursework.repository.MaintenanceEventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceMapper {
    private final EquipmentRepository equipmentRepository;
    private final MaintenanceEventRepository maintenanceEventRepository;

    public MaintenanceDto toDto(Maintenance entity) {
        if (entity == null) {
            return null;
        }

        MaintenanceDto dto = new MaintenanceDto();
        dto.setId(entity.getId());
        dto.setFrequency(entity.getFrequency());
        dto.setEquipmentId(entity.getEquipment() != null ? entity.getEquipment().getId() : null);
        dto.setMaintenanceEventId(entity.getMaintenanceEvent() != null ? entity.getMaintenanceEvent().getId() : null);
        return dto;
    }

    public Maintenance toEntity(MaintenanceDto dto) {
        if (dto == null) {
            return null;
        }

        Maintenance entity = new Maintenance();
        entity.setId(dto.getId());
        entity.setFrequency(dto.getFrequency());

        if (dto.getEquipmentId() != null) {
            entity.setEquipment(equipmentRepository.findById(dto.getEquipmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Обладнання не знайдено")));
        }

        if (dto.getMaintenanceEventId() != null) {
            entity.setMaintenanceEvent(maintenanceEventRepository.findById(dto.getMaintenanceEventId())
                    .orElseThrow(() -> new EntityNotFoundException("Подія обслуговування не знайдена")));
        }

        return entity;
    }
}

