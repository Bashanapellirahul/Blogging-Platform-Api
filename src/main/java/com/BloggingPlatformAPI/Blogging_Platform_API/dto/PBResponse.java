package com.BloggingPlatformAPI.Blogging_Platform_API.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PBResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String  tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
