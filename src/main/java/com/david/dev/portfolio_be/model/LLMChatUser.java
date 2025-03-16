package com.david.dev.portfolio_be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="llm_chat_user")
public class LLMChatUser {

    //TODO: Setup UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long llm_chat_id;

    private Long user_id;

    private String title;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ")
    private Instant updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "llmChatUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LLMChatEntry> llm_chat_entries = new ArrayList<>();

}
