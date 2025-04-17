package com.david.dev.portfolio_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LLMChatEntryDTO {
    private UUID llm_entry_id;
    private UUID llmChatUserId;
    private String text;
    private Boolean isUser;
    private Integer entry_order;
}
