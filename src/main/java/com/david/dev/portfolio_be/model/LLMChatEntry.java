package com.david.dev.portfolio_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a chat entry in a conversation with an LLM (Large Language Model).
 * Each entry can be either from the user or the model, and is associated with a specific chat session.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Private constructor to enforce builder pattern.
@Builder
@Table(name="llm_chat_entry")
public class LLMChatEntry extends BaseEntity{

    @NotNull
    @JsonBackReference  // Prevents infinite recursion during serialization
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private LLMChatUser llmChatUser;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean fromUser;

    @Column(nullable = false)
    private int entryOrder;
}
