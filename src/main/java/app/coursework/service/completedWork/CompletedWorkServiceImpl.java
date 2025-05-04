package app.coursework.service.completedWork;

import app.coursework.dto.CompletedWorkDto;
import app.coursework.mapper.CompletedWorkMapper;
import app.coursework.repository.CompletedWorkRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompletedWorkServiceImpl implements CompletedWorkService {

    private final CompletedWorkRepository completedWorkRepository;
    private final CompletedWorkMapper completedWorkMapper;

    @Override
    public CompletedWorkDto create(CompletedWorkDto dto) {
        var entity = completedWorkMapper.toEntity(dto);
        return completedWorkMapper.toDto(completedWorkRepository.save(entity));
    }

    @Override
    public CompletedWorkDto getById(Long id) {
        return completedWorkMapper.toDto(completedWorkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Виконану роботу не знайдено")));
    }

    @Override
    public List<CompletedWorkDto> getAll() {
        return completedWorkRepository.findAll()
                .stream()
                .map(completedWorkMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompletedWorkDto update(Long id, CompletedWorkDto dto) {
        var entity = completedWorkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Робота не знайдена"));

        entity.setExecutionDate(dto.getExecutionDate());
        entity.setExecutionTime(dto.getExecutionTime());

        if (dto.getMaintenanceId() != null) {
            entity = completedWorkMapper.toEntity(dto);
            entity.setId(id);
        }

        return completedWorkMapper.toDto(completedWorkRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        completedWorkRepository.deleteById(id);
    }
}
