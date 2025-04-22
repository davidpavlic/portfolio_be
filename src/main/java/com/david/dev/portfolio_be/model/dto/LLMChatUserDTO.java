package com.david.dev.portfolio_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LLMChatUserDTO {
    private UUID id;

    //@NotNull
    //private UUID userId;

    @NotBlank
    @Size(max = 255)
    private String title;

    @UpdateTimestamp
    private Instant updatedAt;

    private List<LLMChatEntryDTO> llmChatEntries;
}