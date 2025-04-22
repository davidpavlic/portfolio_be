package com.david.dev.portfolio_be.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LLMChatEntryDTO {
    private UUID id;

    @NotNull
    private UUID llmChatUserId;

    @NotBlank
    private String text;

    private boolean fromUser;

    @Min(value = 0)
    private int entryOrder;
}
