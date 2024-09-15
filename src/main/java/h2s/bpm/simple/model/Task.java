package h2s.bpm.simple.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_instance_id", nullable = false)
    private ProcessInstance processInstance;

    @ManyToOne
    @JoinColumn(name = "activity_definition_id", nullable = false)
    private ActivityDefinition activityDefinition;

    @Column(nullable = false)
    private String status; // e.g., "PENDING", "IN_PROGRESS", "COMPLETED"

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    // Getters and setters
}
