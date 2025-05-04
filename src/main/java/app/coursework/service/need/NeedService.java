package app.coursework.service.need;

import app.coursework.dto.NeedDto;
import java.util.List;

public interface NeedService {
    NeedDto create(NeedDto dto);
    NeedDto getById(Long id);
    List<NeedDto> getAll();
    NeedDto update(Long id, NeedDto dto);
    void delete(Long id);
}
