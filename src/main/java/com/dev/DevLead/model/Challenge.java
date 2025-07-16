package com.dev.DevLead.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "challenges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Challenge title cannot be empty")
    private String title;

    @NotBlank(message = "Challenge description cannot be empty")
    @Column(columnDefinition = "TEXT") // Use TEXT for longer descriptions
    private String description;

    @NotNull(message = "Challenge type cannot be null")
    @Enumerated(EnumType.STRING) // Stores the enum name as a string in the DB
    private ChallengeType type; // ALGORITHMIC or BUILDATHON

    // For ALGORITHMIC challenges: the correct answer/flag
    private String flag;

    // For BUILDATHON challenges: the expected repository URL
    private String buildathonRepoUrl;

    @Min(value = 0, message = "Points cannot be negative")
    private int points; // Points awarded for completion

    @Column(unique = true, nullable = false)
    @Min(value = 1, message = "Order must be at least 1")
    private int order; // The display order of challenges

    // Enum to define challenge types
    public enum ChallengeType {
        ALGORITHMIC,
        BUILDATHON
    }
}

