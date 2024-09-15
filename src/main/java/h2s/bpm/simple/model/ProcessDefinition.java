package h2s.bpm.simple.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "process_definitions")
@Data
public class ProcessDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private String version;

    @OneToMany(mappedBy = "processDefinition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityDefinition> activities;

    @ElementCollection
    private List<String> steps;
    // Getters and setters
}