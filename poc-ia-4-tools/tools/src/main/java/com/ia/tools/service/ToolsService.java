package com.ia.tools.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@Service
public class ToolsService {

    private final ChatClient chatClient;

    public ToolsService(@Qualifier("toolsChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Flux<String> chat(String message, String username) {
        return this.chatClient
                .prompt()
                .system("Resuma a resposta em uma linha. Sejá objetivo e sucinto.")
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, username))
                .user(message)
                .stream()
                .content();
    }
}