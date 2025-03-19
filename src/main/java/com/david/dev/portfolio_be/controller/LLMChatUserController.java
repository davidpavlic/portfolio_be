package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.LLMChatEntry;
import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.model.dto.LLMChatEntryDTO;
import com.david.dev.portfolio_be.service.LLMChatUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/llmchatuser")
public class LLMChatUserController {

    private final LLMChatUserService llmChatUserService;

    public LLMChatUserController(LLMChatUserService llmChatUserService) {
        this.llmChatUserService = llmChatUserService;
    }

    @GetMapping({"", "/"})
    public Collection<LLMChatUser> getAllLLMChats() {
        return llmChatUserService.getAllLLMChats();
    }

    @GetMapping({"{id}", "{id}/"})
    public Optional<LLMChatUser> getLLMChatsByChat(@PathVariable("id") Long id) {
        return llmChatUserService.getAllLLMChatsByChat(id);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<LLMChatUser> createUser(@RequestBody LLMChatUser llmChatUser) {
        LLMChatUser llmChatUserResponse = llmChatUserService.createLLMChatUser(llmChatUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(llmChatUserResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLLMChat(@PathVariable("id") Long id) {
        llmChatUserService.deleteLLMChat(id);
        return ResponseEntity.noContent().build();
    }

}
