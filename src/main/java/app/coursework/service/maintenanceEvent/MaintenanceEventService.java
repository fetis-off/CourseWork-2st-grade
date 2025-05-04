package app.coursework.service.maintenanceEvent;

import app.coursework.dto.MaintenanceEventDto;
import java.util.List;

public interface MaintenanceEventService {
    MaintenanceEventDto create(MaintenanceEventDto dto);
    MaintenanceEventDto getById(Long id);
    List<MaintenanceEventDto> getAll();
    MaintenanceEventDto update(Long id, MaintenanceEventDto dto);
    void delete(Long id);
}
