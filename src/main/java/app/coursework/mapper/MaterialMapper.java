package app.coursework.mapper;

import app.coursework.dto.MaterialDto;
import app.coursework.model.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public MaterialDto toDto(Material entity) {
        if (entity == null) {
            return null;
        }

        MaterialDto dto = new MaterialDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());

        return dto;
    }

    public Material toEntity(MaterialDto dto) {
        if (dto == null) {
            return null;
        }

        Material entity = new Material();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());

        return entity;
    }
}
