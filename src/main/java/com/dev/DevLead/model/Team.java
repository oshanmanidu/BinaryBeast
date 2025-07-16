package com.dev.DevLead.model;


import jakarta.persistence.*; // JPA annotations
import jakarta.validation.constraints.NotBlank; // Validation annotations
import jakarta.validation.constraints.Size;
import lombok.Data; // Lombok for boilerplate code
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime; // For timestamps

@Entity // Marks this class as a JPA entity
@Table(name = "teams") // Maps to the 'teams' table in the database
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class Team {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments ID
    private Long id;

    @Column(unique = true, nullable = false) // Must be unique and not null
    @NotBlank(message = "Team name cannot be empty")
    @Size(min = 3, max = 50, message = "Team name must be between 3 and 50 characters")
    private String teamName;

    @Column(nullable = false) // Not null
    @NotBlank(message = "Password cannot be empty")
    private String passwordHash; // Stores the hashed password (use BCryptPasswordEncoder)

    // Optional: Email field if you decide to include it
    // private String email;

    @Column(nullable = false)
    private int totalPoints = 0; // Default to 0 points

    @Column(nullable = false, updatable = false) // Set once, not updatable via JPA
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist // Called before the entity is first saved to the database
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate // Called before the entity is updated in the database
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
