package h2s.bpm.simple.repository;

import h2s.bpm.simple.model.ProcessDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessDefinitionRepository extends JpaRepository<ProcessDefinition, Long> {
}
