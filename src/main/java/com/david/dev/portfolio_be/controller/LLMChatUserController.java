package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.dto.LLMChatUserDTO;
import com.david.dev.portfolio_be.service.LLMChatUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/llmchatuser")
public class LLMChatUserController {

    private final LLMChatUserService llmChatUserService;

    public LLMChatUserController(LLMChatUserService llmChatUserService) {
        this.llmChatUserService = llmChatUserService;
    }

    @GetMapping({"", "/"})
    public List<LLMChatUserDTO> getAllLLMChats() {
        return llmChatUserService.getAllLLMChats();
    }

    @GetMapping({"{id}", "{id}/"})
    public LLMChatUserDTO getLLMChatsByChat(@PathVariable("id") UUID id) {
        return llmChatUserService.getAllLLMChatsByChat(id);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<LLMChatUserDTO> createUser(@RequestBody @Valid LLMChatUserDTO llmChatUserDto) {
        return ResponseEntity.status(201).body(
                llmChatUserService.createLLMChatUser(llmChatUserDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLLMChat(@PathVariable("id") UUID id) {
        return llmChatUserService.deleteLLMChat(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
