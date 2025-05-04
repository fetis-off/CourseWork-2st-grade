package app.coursework.service.equipment;

import app.coursework.dto.EquipmentDto;
import app.coursework.mapper.EquipmentMapper;
import app.coursework.model.Equipment;
import app.coursework.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    @Override
    public EquipmentDto create(EquipmentDto dto) {
        Equipment equipment = equipmentMapper.toEntity(dto);
        return equipmentMapper.toDto(equipmentRepository.save(equipment));
    }

    @Override
    public EquipmentDto getById(Long id) {
        return equipmentMapper.toDto(equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Обладнання не знайдено")));
    }

    @Override
    public List<EquipmentDto> getAll() {
        return equipmentRepository.findAll()
                .stream()
                .map(equipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EquipmentDto update(Long id, EquipmentDto dto) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Обладнання не знайдено"));

        equipment.setName(dto.getName());
        equipment.setPrice(dto.getPrice());
        if (dto.getParentId() != null) {
            Equipment parent = equipmentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Батьківське обладнання не знайдено"));
            equipment.setParent(parent);
        } else {
            equipment.setParent(null);
        }

        return equipmentMapper.toDto(equipmentRepository.save(equipment));
    }

    @Override
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }
}
