package app.coursework.mapper;

import app.coursework.dto.EquipmentDto;
import app.coursework.model.Equipment;
import app.coursework.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentMapper {
    private final EquipmentRepository equipmentRepository;

    public EquipmentDto toDto(Equipment entity) {
        if (entity == null) {
            return null;
        }

        EquipmentDto dto = new EquipmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setParentId(entity.getParent() != null ? entity.getParent().getId() : null);

        return dto;
    }

    public Equipment toEntity(EquipmentDto dto) {
        if (dto == null) {
            return null;
        }

        Equipment entity = new Equipment();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        if (dto.getParentId() != null) {
            entity.setParent(equipmentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent equipment not found")));
        }

        return entity;
    }
}
