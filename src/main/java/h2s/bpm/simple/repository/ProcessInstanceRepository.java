package h2s.bpm.simple.repository;

import h2s.bpm.simple.model.ProcessInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessInstanceRepository extends JpaRepository<ProcessInstance, Long> {
}
