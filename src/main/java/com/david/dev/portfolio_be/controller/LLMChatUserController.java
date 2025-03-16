package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.LLMChatUser;
import com.david.dev.portfolio_be.service.LLMChatUserService;
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

}
