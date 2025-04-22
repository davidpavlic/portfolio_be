package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
import org.springframework.stereotype.Component;

@Component
public class LLMChatEntryMapper {

    public LLMChatEntryDTO toDTO(LLMChatEntry entry) {
        return new LLMChatEntryDTO(
                entry.getId(),
                entry.getLlmChatUser().getId(),
                entry.getText(),
                entry.isFromUser(),
                entry.getEntryOrder()
        );
    }

    public LLMChatEntry toEntity(LLMChatEntryDTO dto, LLMChatUser llmChatUser) {
        return LLMChatEntry.builder()
                .llmChatUser(llmChatUser)
                .text(dto.getText())
                .fromUser(dto.isFromUser())
                .entryOrder(dto.getEntryOrder())
                .build();
    }
}
