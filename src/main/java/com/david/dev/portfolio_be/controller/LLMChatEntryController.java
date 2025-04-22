package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
import com.david.dev.portfolio_be.service.LLMChatEntryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/llmchatentry")
public class LLMChatEntryController {

    private final LLMChatEntryService llmChatEntryService;

    public LLMChatEntryController(LLMChatEntryService llmChatEntryService) {
        this.llmChatEntryService = llmChatEntryService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<LLMChatEntryDTO> createChatEntry(@RequestBody @Valid LLMChatEntryDTO llmChatEntryDto) {
        return ResponseEntity.status(201).body(
                llmChatEntryService.createLLMChatEntry(llmChatEntryDto)
        );
    }

}
