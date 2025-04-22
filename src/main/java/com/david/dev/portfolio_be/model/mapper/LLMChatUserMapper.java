package com.david.dev.portfolio_be.model.mapper;

import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.model.dto.LLMChatUserDTO;

public class LLMChatUserMapper {

    public static LLMChatUserDTO toDTO(LLMChatUser entry) {
        return new LLMChatUserDTO(
                entry.getId(),
                entry.getTitle(),
                entry.getUpdatedAt(),
                entry.getLlmChatEntries() != null ? entry.getLlmChatEntries().stream()
                        .map(LLMChatEntryMapper::toDTO)
                        .toList() : null
        );
    }

    public static LLMChatUser toEntity(LLMChatUserDTO dto) {
        return LLMChatUser.builder()
                .title(dto.getTitle())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
