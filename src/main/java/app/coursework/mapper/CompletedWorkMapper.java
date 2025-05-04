package app.coursework.mapper;

import app.coursework.dto.CompletedWorkDto;
import app.coursework.model.CompletedWork;
import app.coursework.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompletedWorkMapper {

    private final MaintenanceRepository maintenanceRepository;

    public CompletedWorkDto toDto(CompletedWork entity) {
        if (entity == null) {
            return null;
        }

        CompletedWorkDto dto = new CompletedWorkDto();
        dto.setId(entity.getId());
        dto.setExecutionDate(entity.getExecutionDate());
        dto.setExecutionTime(entity.getExecutionTime());
        dto.setMaintenanceId(entity.getMaintenance() != null ? entity.getMaintenance().getId() : null);
        return dto;
    }

    public CompletedWork toEntity(CompletedWorkDto dto) {
        if (dto == null) {
            return null;
        }

        CompletedWork entity = new CompletedWork();
        entity.setId(dto.getId());
        entity.setExecutionDate(dto.getExecutionDate());
        entity.setExecutionTime(dto.getExecutionTime());

        if (dto.getMaintenanceId() != null) {
            entity.setMaintenance(maintenanceRepository.findById(dto.getMaintenanceId())
                    .orElseThrow(() -> new EntityNotFoundException("Обслуговування не знайдено")));
        }

        return entity;
    }
}

