package app.coursework.service.completedWork;

import app.coursework.dto.CompletedWorkDto;
import java.util.List;

public interface CompletedWorkService {
    CompletedWorkDto create(CompletedWorkDto dto);
    CompletedWorkDto getById(Long id);
    List<CompletedWorkDto> getAll();
    CompletedWorkDto update(Long id, CompletedWorkDto dto);
    void delete(Long id);
}
