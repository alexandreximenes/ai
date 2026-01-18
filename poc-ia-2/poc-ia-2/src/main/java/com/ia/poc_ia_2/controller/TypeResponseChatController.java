package com.ia.poc_ia_2.controller;

import com.ia.poc_ia_2.domain.Country;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/api")
public class TypeResponseChatController {

    private final ChatClient ollamaChatClient;

    @Value("classpath:/promptTemplates/architecturePromptTemplate.st")
    private Resource architecturePromptTemplate;

    public TypeResponseChatController(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.ollamaChatClient = ollamaChatClient;
    }


    @GetMapping(value = "/type-response/ollama/chat")
    public Country typeResponseOllamaChat(@RequestParam String message){
        return this.ollamaChatClient
                .prompt(message)
                .advisors(new SimpleLoggerAdvisor())
//                .system(architecturePromptTemplate)
                .options(OllamaChatOptions.builder()
                        .model(OllamaModel.LLAMA3_2_3B)
                        .temperature(0.3)
                        .topP(0.9)
//                        .numPredict(100)
                        .build())
                .call().entity(Country.class);
    }

    @GetMapping(value = "/list/ollama/chat")
    public List<String> listResponseOllamaChat(@RequestParam String message){
        return this.ollamaChatClient
                .prompt(message)
                .advisors(new SimpleLoggerAdvisor())
                .options(OllamaChatOptions.builder()
                        .model(OllamaModel.LLAMA3_2_3B)
                        .temperature(0.3)
                        .topP(0.9)
                        .build())
                .call().entity(new ListOutputConverter());
    }

    @GetMapping(value = "/map/ollama/chat")
    public Map<String, Object> mapResponseOllamaChat(@RequestParam String message){
        return this.ollamaChatClient
                .prompt(message)
                .advisors(new SimpleLoggerAdvisor())
                .options(OllamaChatOptions.builder()
                        .model(OllamaModel.LLAMA3_2_3B)
                        .temperature(0.3)
                        .topP(0.9)
                        .build())
                .call().entity(new MapOutputConverter());
    }

    @GetMapping(value = "/list-bean/ollama/chat")
    public Country listBeanResponseOllamaChat(@RequestParam String message){
        return this.ollamaChatClient
                .prompt(message)
                .advisors(new SimpleLoggerAdvisor())
                .options(OllamaChatOptions.builder()
                        .model(OllamaModel.LLAMA3_2_3B)
                        .temperature(0.3)
                        .topP(0.9)
                        .build())
//                .call().entity(new ParameterizedTypeReference<>() {});
                .call().entity(Country.class);
    }
}
