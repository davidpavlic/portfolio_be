package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
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

    public LLMChatEntry createLLMChatEntry(LLMChatEntryDTO llmChatEntryDto) {
        LLMChatEntry llmChatEntry = new LLMChatEntry(llmChatUserRepository.getReferenceById(llmChatEntryDto.getLlmChatUserId()), llmChatEntryDto.getText(), llmChatEntryDto.getIsUser(), llmChatEntryDto.getEntry_order());
        return llmChatEntryRepository.save(llmChatEntry);
    }


}
