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
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/api")
public class StreamChatController {

    private final ChatClient ollamaChatClient;

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    private Resource systemPromptTemplate;

    public StreamChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping(value = "/stream/ollama/chat")
    public Flux<String> streamOllamaChat(@RequestParam String message){
        return this.ollamaChatClient
                .prompt(message)
                .system(systemPromptTemplate)
                .options(OllamaChatOptions.builder()
                        .model(OllamaModel.LLAMA3_2_3B)
                        .temperature(0.3)
                        .topP(0.9)
                        .numPredict(100)
                        .build())
//                .system("Sou arquiteto de sofware, pronto para responder perguntas tecnicas relacionadas a projetos, design system, e tudo relacionado a arquitetura de software e IA")
//                .user("Em que posso ajudar vc?")
                .stream().content();
    }
}
