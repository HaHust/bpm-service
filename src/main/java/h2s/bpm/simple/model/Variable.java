package h2s.bpm.simple.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "variables")
public class Variable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String type; // e.g., "string", "number", "boolean"

    @ManyToOne
    @JoinColumn(name = "process_instance_id")
    private ProcessInstance processInstance;

    @ManyToOne
    @JoinColumn(name = "task_instance_id")
    private Task taskInstance;

    // Getters and setters
}

