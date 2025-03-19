package com.david.dev.portfolio_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LLMChatEntryDTO {
    private Long llm_entry_id;
    private Long llmChatUserId;
    private String text;
    private Boolean isUser;
    private Integer entry_order;
}
