package app.coursework.service.maintenance;

import app.coursework.dto.MaintenanceDto;
import java.util.List;

public interface MaintenanceService {
    MaintenanceDto create(MaintenanceDto dto);
    MaintenanceDto getById(Long id);
    List<MaintenanceDto> getAll();
    MaintenanceDto update(Long id, MaintenanceDto dto);
    void delete(Long id);
}
