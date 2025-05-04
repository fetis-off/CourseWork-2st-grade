package app.coursework.repository;

import app.coursework.model.MaintenanceEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceEventRepository extends JpaRepository<MaintenanceEvent, Long> {
}
