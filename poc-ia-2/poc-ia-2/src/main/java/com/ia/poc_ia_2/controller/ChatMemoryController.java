package com.ia.poc_ia_2.controller;

import com.ia.poc_ia_2.domain.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/v1/api")
public class ChatMemoryController {

    private final ChatClient chatMemoryClient;

    public ChatMemoryController(@Qualifier("chatMemoryClient") ChatClient chatMemoryClient) {
        this.chatMemoryClient = chatMemoryClient;
    }


    @GetMapping("/memory/chat")
    public String memoryChat(@RequestParam String message, @RequestParam String userName){
        return this.chatMemoryClient
                .prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, userName))
                .call().content();
    }
}
