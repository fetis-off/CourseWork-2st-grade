package app.coursework.service.material;

import app.coursework.dto.MaterialDto;
import java.util.List;

public interface MaterialService {
    MaterialDto create(MaterialDto dto);
    MaterialDto getById(Long id);
    List<MaterialDto> getAll();
    MaterialDto update(Long id, MaterialDto dto);
    void delete(Long id);
}
