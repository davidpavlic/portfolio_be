package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.repository.LLMChatUserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class LLMChatUserService {

    private final LLMChatUserRepository llmChatUserRepository;

    public LLMChatUserService(LLMChatUserRepository llmChatUserRepository) {
        this.llmChatUserRepository = llmChatUserRepository;
    }

    public Collection<LLMChatUser> getAllLLMChats() {
        return llmChatUserRepository.findAllByOrderByUpdatedAtDesc();
    }

    public Optional<LLMChatUser> getAllLLMChatsByChat(UUID id) {
        return llmChatUserRepository.findById(id);
    }

    public LLMChatUser createLLMChatUser(LLMChatUser llmChatUser){
        return llmChatUserRepository.save(llmChatUser);
    }

    public void deleteLLMChat(UUID id) {
        llmChatUserRepository.deleteById(id);
    }

}
