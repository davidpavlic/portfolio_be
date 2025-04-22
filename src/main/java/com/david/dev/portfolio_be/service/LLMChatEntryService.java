package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
import com.david.dev.portfolio_be.model.mapper.LLMChatEntryMapper;
import com.david.dev.portfolio_be.repository.LLMChatEntryRepository;
import com.david.dev.portfolio_be.repository.LLMChatUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LLMChatEntryService {

    private final LLMChatEntryRepository llmChatEntryRepository;
    private final LLMChatUserRepository llmChatUserRepository;

    public LLMChatEntryService(LLMChatEntryRepository llmChatEntryRepository, LLMChatUserRepository llmChatUserRepository) {
        this.llmChatEntryRepository = llmChatEntryRepository;
        this.llmChatUserRepository = llmChatUserRepository;
    }

    public LLMChatEntryDTO createLLMChatEntry(LLMChatEntryDTO llmChatEntryDto) {
        LLMChatEntry llmChatEntry = LLMChatEntry.builder()
                .llmChatUser(llmChatUserRepository.getReferenceById(llmChatEntryDto.getLlmChatUserId()))
                .text(llmChatEntryDto.getText())
                .fromUser(llmChatEntryDto.isFromUser())  // Changed to isUser() for boolean getter
                .entryOrder(llmChatEntryDto.getEntryOrder())  // Match renamed field
                .build();

        return LLMChatEntryMapper.toDTO(llmChatEntryRepository.save(llmChatEntry));
    }
}