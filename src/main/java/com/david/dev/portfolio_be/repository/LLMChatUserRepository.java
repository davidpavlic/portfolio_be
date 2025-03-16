package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.LLMChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LLMChatUserRepository extends JpaRepository<LLMChatUser, Long> {

    List<LLMChatUser> findAllByOrderByUpdatedAtDesc();

}
