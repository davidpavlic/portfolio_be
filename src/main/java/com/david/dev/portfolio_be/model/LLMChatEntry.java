package com.david.dev.portfolio_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="llm_chat_entry")
public class LLMChatEntry {

    @Id
    @GeneratedValue
    private UUID llm_entry_id;

    @NotNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "llm_chat_id")
    private LLMChatUser llmChatUser;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String text;

    @NotNull
    private Boolean isUser;

    @NotNull
    private Integer entry_order;

    public LLMChatEntry(LLMChatUser llmChatUser, String text, Boolean isUser, Integer entry_order) {
        this.llmChatUser = llmChatUser;
        this.text = text;
        this.isUser = isUser;
        this.entry_order = entry_order;
    }
}
