package com.david.dev.portfolio_be.repository;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LLMChatEntryRepository extends JpaRepository<LLMChatEntry, Long> {
}
