package app.coursework.service.material;

import app.coursework.dto.MaterialDto;
import app.coursework.mapper.MaterialMapper;
import app.coursework.model.Material;
import app.coursework.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public MaterialDto create(MaterialDto dto) {
        Material material = materialMapper.toEntity(dto);
        return materialMapper.toDto(materialRepository.save(material));
    }

    @Override
    public MaterialDto getById(Long id) {
        return materialMapper.toDto(materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Матеріал не знайдено")));
    }

    @Override
    public List<MaterialDto> getAll() {
        return materialRepository.findAll()
                .stream()
                .map(materialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDto update(Long id, MaterialDto dto) {
        Material existing = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Матеріал не знайдено"));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setType(dto.getType());
        return materialMapper.toDto(materialRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
