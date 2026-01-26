package com.ia.tools.config;

import com.ia.tools.tools.TimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ToolChatConfig {

    @Bean("toolsChatClient")
    public ChatClient toolsChatClient(OllamaChatModel ollamaChatModel, TimeTools timeTools) {
        return ChatClient.builder(ollamaChatModel)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor()))
                .defaultTools(timeTools)
                .build();
    }
}
