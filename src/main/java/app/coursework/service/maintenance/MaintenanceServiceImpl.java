package app.coursework.service.maintenance;

import app.coursework.dto.MaintenanceDto;
import app.coursework.mapper.MaintenanceMapper;
import app.coursework.repository.MaintenanceRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceDto create(MaintenanceDto dto) {
        var entity = maintenanceMapper.toEntity(dto);
        return maintenanceMapper.toDto(maintenanceRepository.save(entity));
    }

    @Override
    public MaintenanceDto getById(Long id) {
        return maintenanceMapper.toDto(maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Обслуговування не знайдено")));
    }

    @Override
    public List<MaintenanceDto> getAll() {
        return maintenanceRepository.findAll()
                .stream()
                .map(maintenanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaintenanceDto update(Long id, MaintenanceDto dto) {
        var entity = maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Обслуговування не знайдено"));

        entity.setFrequency(dto.getFrequency());

        if (dto.getEquipmentId() != null || dto.getMaintenanceEventId() != null) {
            entity = maintenanceMapper.toEntity(dto);
            entity.setId(id);
        }

        return maintenanceMapper.toDto(maintenanceRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        maintenanceRepository.deleteById(id);
    }
}
