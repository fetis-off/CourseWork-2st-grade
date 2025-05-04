package app.coursework.mapper;

import app.coursework.dto.NeedDto;
import app.coursework.model.Need;
import app.coursework.repository.MaintenanceRepository;
import app.coursework.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NeedMapper {
    private final MaterialRepository materialRepository;
    private final MaintenanceRepository maintenanceRepository;

    public NeedDto toDto(Need entity) {
        if (entity == null) {
            return null;
        }

        NeedDto dto = new NeedDto();
        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setMaterialId(entity.getMaterial() != null ? entity.getMaterial().getId() : null);
        dto.setMaintenanceId(entity.getMaintenance() != null ? entity.getMaintenance().getId() : null);
        return dto;
    }

    public Need toEntity(NeedDto dto) {
        if (dto == null) {
            return null;
        }

        Need entity = new Need();
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());

        if (dto.getMaterialId() != null) {
            entity.setMaterial(materialRepository.findById(dto.getMaterialId())
                    .orElseThrow(() -> new EntityNotFoundException("Матеріал не знайдено")));
        }

        if (dto.getMaintenanceId() != null) {
            entity.setMaintenance(maintenanceRepository.findById(dto.getMaintenanceId())
                    .orElseThrow(() -> new EntityNotFoundException("Обслуговування не знайдено")));
        }

        return entity;
    }
}

