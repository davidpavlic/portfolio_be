package com.david.dev.portfolio_be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in a chat session with an LLM (Large Language Model).
 * Each user can have multiple chat entries associated with them.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name="llm_chat_user")
public class LLMChatUser extends BaseEntity{

    //@NotNull
    //@JsonBackReference
    //@ManyToOne
    @JoinColumn(name = "user_id"/*, nullable = false*/)
    private Long userId;

    @NotBlank
    @Column(length = 255, nullable = false)
    private String title;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMPTZ")
    private Instant updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "llmChatUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LLMChatEntry> llmChatEntries = new ArrayList<>();

}
