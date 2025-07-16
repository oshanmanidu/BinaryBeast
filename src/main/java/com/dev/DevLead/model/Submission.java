package com.dev.DevLead.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Key to Team
    @NotNull(message = "Team ID cannot be null")
    private Long teamId;

    // Foreign Key to Challenge
    @NotNull(message = "Challenge ID cannot be null")
    private Long challengeId;

    @Column(nullable = false)
    private LocalDateTime submissionTime;

    @Column(columnDefinition = "TEXT") // Code submitted by the team (for ALGORITHMIC)
    private String codeSubmitted;

    private String flagSubmitted; // Flag provided by the team (for ALGORITHMIC)

    private String buildathonRepoUrlSubmitted; // Repository URL submitted (for BUILDATHON)

    @NotBlank(message = "Submission status cannot be empty")
    private String status; // e.g., "ACCEPTED", "WRONG_ANSWER", "FAILED", "PENDING_REVIEW"

    @Column(columnDefinition = "TEXT") // Output from Judge0 or internal validation (for ALGORITHMIC)
    private String output;

    @PrePersist // Set submission time automatically on creation
    protected void onCreate() {
        this.submissionTime = LocalDateTime.now();
    }
}
