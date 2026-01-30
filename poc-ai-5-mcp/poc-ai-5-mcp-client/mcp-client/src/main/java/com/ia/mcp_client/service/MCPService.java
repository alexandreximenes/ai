package com.ia.mcp_client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MCPService {

    private final ChatClient chatClient;

    public MCPService(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    public Flux<String> chat(String message, String username) {
        return chatClient
                .prompt()
                .system(promptSystemSpec -> promptSystemSpec
                        .param("username", username)
                        .text("You are a helpful assistant. The user name is {{username}}."))
                .user(message)
                .stream()
                .content();
    }
}
