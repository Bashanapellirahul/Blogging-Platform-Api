package com.BloggingPlatformAPI.Blogging_Platform_API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personalBlogging")
public class PersonalBlogging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false, length = 255)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false, length = 255)
    private String content;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String category;
    @Column(columnDefinition = "TEXT")
    private String tags;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
