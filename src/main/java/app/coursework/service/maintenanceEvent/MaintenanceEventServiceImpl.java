package app.coursework.service.maintenanceEvent;

import app.coursework.dto.MaintenanceEventDto;
import app.coursework.mapper.MaintenanceEventMapper;
import app.coursework.repository.MaintenanceEventRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceEventServiceImpl implements MaintenanceEventService {

    private final MaintenanceEventRepository maintenanceEventRepository;
    private final MaintenanceEventMapper maintenanceEventMapper;

    @Override
    public MaintenanceEventDto create(MaintenanceEventDto dto) {
        var entity = maintenanceEventMapper.toEntity(dto);
        return maintenanceEventMapper.toDto(maintenanceEventRepository.save(entity));
    }

    @Override
    public MaintenanceEventDto getById(Long id) {
        return maintenanceEventMapper.toDto(maintenanceEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Подія обслуговування не знайдена")));
    }

    @Override
    public List<MaintenanceEventDto> getAll() {
        return maintenanceEventRepository.findAll()
                .stream()
                .map(maintenanceEventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaintenanceEventDto update(Long id, MaintenanceEventDto dto) {
        var entity = maintenanceEventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Подія не знайдена"));
        entity.setName(dto.getName());
        entity.setProcedureDescription(dto.getProcedureDescription());
        return maintenanceEventMapper.toDto(maintenanceEventRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        maintenanceEventRepository.deleteById(id);
    }
}
