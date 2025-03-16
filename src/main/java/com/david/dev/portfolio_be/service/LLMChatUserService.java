package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.repository.LLMChatUserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LLMChatUserService {

    private final LLMChatUserRepository llmChatUserRepository;

    public LLMChatUserService(LLMChatUserRepository llmChatUserRepository) {
        this.llmChatUserRepository = llmChatUserRepository;
    }

    public Collection<LLMChatUser> getAllLLMChats() {
        return llmChatUserRepository.findAllByOrderByUpdatedAtDesc();
    }

    public Optional<LLMChatUser> getAllLLMChatsByChat(Long id) {
        return llmChatUserRepository.findById(id);
    }
}
