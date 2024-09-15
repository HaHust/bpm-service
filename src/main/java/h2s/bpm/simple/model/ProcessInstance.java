package h2s.bpm.simple.model;


import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "process_instances")
public class ProcessInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_definition_id", nullable = false)
    private ProcessDefinition processDefinition;

    @Column(nullable = false)
    private String status; // e.g., "ACTIVE", "COMPLETED", "TERMINATED"

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "current_activity_id")
    private Long currentActivityId;

    @Column(name = "currentStep")
    private Integer currentStep;
    // Getters and setters
}
