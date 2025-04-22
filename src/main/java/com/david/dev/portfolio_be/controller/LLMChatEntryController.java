package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
import com.david.dev.portfolio_be.service.LLMChatEntryService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<LLMChatEntry> createUser(@RequestBody LLMChatEntryDTO llmChatEntryDto) {
        LLMChatEntry llmChatEntry = llmChatEntryService.createLLMChatEntry(llmChatEntryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(llmChatEntry);
    }

}
