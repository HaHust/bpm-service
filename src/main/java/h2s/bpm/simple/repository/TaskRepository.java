package h2s.bpm.simple.repository;

import h2s.bpm.simple.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProcessInstanceId(Long processInstanceId);
}
