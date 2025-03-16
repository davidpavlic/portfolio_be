package com.david.dev.portfolio_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="llm_chat_entry")
public class LLMChatEntry {

    //TODO: Setup UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long llm_entry_id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "llm_chat_id")
    private LLMChatUser llmChatUser;

    private String text;

    private Boolean isUser;

    private Integer entry_order;

}
