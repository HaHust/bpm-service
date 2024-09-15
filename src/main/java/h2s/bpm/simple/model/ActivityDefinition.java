package h2s.bpm.simple.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "activity_definitions")
public class ActivityDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // e.g., "task", "gateway", "event"

    @ManyToOne
    @JoinColumn(name = "process_definition_id", nullable = false)
    private ProcessDefinition processDefinition;

    @Column(name = "next_activity_id")
    private String nextActivityId;

    // For gateways
    @Column(name = "condition_expression")
    private String conditionExpression;

    // Getters and setters
}

