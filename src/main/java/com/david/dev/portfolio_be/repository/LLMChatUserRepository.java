package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.LLMChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LLMChatUserRepository extends JpaRepository<LLMChatUser, UUID> {

    List<LLMChatUser> findAllByOrderByUpdatedAtDesc();

}
