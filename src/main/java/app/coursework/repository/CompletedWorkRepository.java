package app.coursework.repository;

import app.coursework.model.CompletedWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedWorkRepository extends JpaRepository<CompletedWork, Long> {
}
