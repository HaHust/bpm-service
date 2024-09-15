package h2s.bpm.simple.service;

import h2s.bpm.simple.model.Task;
import h2s.bpm.simple.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Task createTask(Task task) {
        task.setStatus("PENDING");
        task.setStartTime(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Optional<Task> getTask(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public Task updateTask(Long taskId, Task updatedTask) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setStatus(updatedTask.getStatus());
                    task.setAssignee(updatedTask.getAssignee());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Transactional
    public Task completeTask(Long taskId) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setStatus("COMPLETED");
                    task.setEndTime(LocalDateTime.now());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByProcessInstance(Long processInstanceId) {
        return taskRepository.findByProcessInstanceId(processInstanceId);
    }
}