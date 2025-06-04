package com.david.dev.portfolio_be.controller;

import com.david.dev.portfolio_be.model.EmailRequest;
import com.david.dev.portfolio_be.service.MainEmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/main")
@Validated
public class MainEmailController {

    private final MainEmailService mainEmailService;

    public MainEmailController(MainEmailService mainEmailService) {
        this.mainEmailService = mainEmailService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Boolean> createProjectCard(@Valid @RequestBody EmailRequest request) throws IOException {
        return ResponseEntity.status(201).body(
                mainEmailService.sendEmail(request.getCaption(), request.getDescription())
        );
    }

}
