package com.david.dev.portfolio_be.service;

import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.model.dto.LLMChatUserDTO;
import com.david.dev.portfolio_be.model.mapper.LLMChatUserMapper;
import com.david.dev.portfolio_be.repository.LLMChatUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LLMChatUserService {

    private final LLMChatUserRepository llmChatUserRepository;

    public LLMChatUserService(LLMChatUserRepository llmChatUserRepository) {
        this.llmChatUserRepository = llmChatUserRepository;
    }

    public List<LLMChatUserDTO> getAllLLMChats() {
        return llmChatUserRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(LLMChatUserMapper::toDTO)
                .toList();
    }

    public LLMChatUserDTO getAllLLMChatsByChat(UUID id) {
        LLMChatUser llmChatUser = llmChatUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LLMChatUser not found with id: " + id));

        return LLMChatUserMapper.toDTO(llmChatUser);
    }

    public LLMChatUserDTO createLLMChatUser(LLMChatUserDTO llmChatUserDto){
        LLMChatUser llmChatUser = LLMChatUserMapper.toEntity(llmChatUserDto);

        return LLMChatUserMapper.toDTO(llmChatUserRepository.save(llmChatUser));
    }

    public boolean deleteLLMChat(UUID id) {
        Optional<LLMChatUser> optional = llmChatUserRepository.findById(id);
        if (optional.isEmpty()) return false;

        llmChatUserRepository.deleteById(id);
        return true;
    }

}
