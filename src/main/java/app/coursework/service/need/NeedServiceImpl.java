package app.coursework.service.need;

import app.coursework.dto.NeedDto;
import app.coursework.mapper.NeedMapper;
import app.coursework.repository.NeedRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NeedServiceImpl implements NeedService {

    private final NeedRepository needRepository;
    private final NeedMapper needMapper;

    @Override
    public NeedDto create(NeedDto dto) {
        var entity = needMapper.toEntity(dto);
        return needMapper.toDto(needRepository.save(entity));
    }

    @Override
    public NeedDto getById(Long id) {
        return needMapper.toDto(needRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Потреба не знайдена")));
    }

    @Override
    public List<NeedDto> getAll() {
        return needRepository.findAll()
                .stream()
                .map(needMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NeedDto update(Long id, NeedDto dto) {
        var entity = needRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Потреба не знайдена"));
        entity.setQuantity(dto.getQuantity());

        entity = needMapper.toEntity(dto);
        entity.setId(id);

        return needMapper.toDto(needRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        needRepository.deleteById(id);
    }
}
