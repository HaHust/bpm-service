package h2s.bpm.simple.repository;

import h2s.bpm.simple.model.ActivityDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDefinitionRepository extends JpaRepository<ActivityDefinition, Long> {
}
