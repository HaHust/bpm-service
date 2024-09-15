package h2s.bpm.simple.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_instance_id", nullable = false)
    private ProcessInstance processInstance;

    @Column(nullable = false)
    private String action; // e.g., "STARTED", "COMPLETED", "TASK_ASSIGNED"

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "user_id")
    private String userId;

    @Column
    private String details;

    // Getters and setters
}
