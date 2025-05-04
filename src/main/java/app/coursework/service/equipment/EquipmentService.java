package app.coursework.service.equipment;

import app.coursework.dto.EquipmentDto;
import java.util.List;

public interface EquipmentService {
    EquipmentDto create(EquipmentDto dto);
    EquipmentDto getById(Long id);
    List<EquipmentDto> getAll();
    EquipmentDto update(Long id, EquipmentDto dto);
    void delete(Long id);
}
