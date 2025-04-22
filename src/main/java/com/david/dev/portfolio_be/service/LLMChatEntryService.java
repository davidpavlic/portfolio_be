package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
import com.david.dev.portfolio_be.model.mapper.LLMChatEntryMapper;
import com.david.dev.portfolio_be.repository.LLMChatEntryRepository;
import com.david.dev.portfolio_be.repository.LLMChatUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LLMChatEntryService {

    private final LLMChatEntryRepository llmChatEntryRepository;
    private final LLMChatUserRepository llmChatUserRepository;

    public LLMChatEntryService(LLMChatEntryRepository llmChatEntryRepository, LLMChatUserRepository llmChatUserRepository) {
        this.llmChatEntryRepository = llmChatEntryRepository;
        this.llmChatUserRepository = llmChatUserRepository;
    }

    @Transactional
    public LLMChatEntryDTO createLLMChatEntry(LLMChatEntryDTO llmChatEntryDto) {
        LLMChatUser llmChatUser = llmChatUserRepository.getReferenceById(llmChatEntryDto.getLlmChatUserId());

        LLMChatEntry llmChatEntry = LLMChatEntry.builder()
                .llmChatUser(llmChatUser)
                .text(llmChatEntryDto.getText())
                .fromUser(llmChatEntryDto.isFromUser())
                .entryOrder(llmChatEntryDto.getEntryOrder())
                .build();

        llmChatUser.setUpdatedAt(Instant.now());
        llmChatUserRepository.save(llmChatUser);

        return LLMChatEntryMapper.toDTO(llmChatEntryRepository.save(llmChatEntry));
    }
}