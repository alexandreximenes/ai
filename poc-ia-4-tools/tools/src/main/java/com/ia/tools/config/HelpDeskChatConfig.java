package com.ia.tools.config;

import com.ia.tools.tools.TimeTools;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.tool.execution.DefaultToolExecutionExceptionProcessor;
import org.springframework.ai.tool.execution.ToolExecutionException;
import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Objects;

@Configuration
public class HelpDeskChatConfig {

    @Value("classpath:promptTemplates/helpDeskSystemPromptTemplate.st")
    Resource systemPromptTemplate;

    @PostConstruct
    public void validate() {
        if (!systemPromptTemplate.exists()) {
            throw new IllegalStateException("Arquivo de prompt não encontrado: helpDeskSystemPromptTemplate.st");
        }
    }

    ChatOptions chatOptions = ChatOptions.builder()
            .temperature(0.3)                       //Controla a criatividade das respostas geradas
            .topP(0.9)                              //Controla a diversidade das respostas geradas
            .presencePenalty(0.0)                   //Evita que o modelo mude de assunto com muita frequência
            .frequencyPenalty(0.3)                  //Evita repetição excessiva de palavras ou frases
//            .maxTokens(350)                         //Define o tamanho máximo da resposta gerada
            .stopSequences(List.of("FIM"))   //Define sequências que indicam o fim da resposta
            .build();

    @Bean("helpDeskChatClient")
    public ChatClient helpDeskChatClient(OllamaChatModel ollamaChatModel, ChatMemory chatMemory,
                                         TimeTools timeTools) {
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(ollamaChatModel)
                .defaultOptions(chatOptions)
                .defaultSystem(systemPromptTemplate)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), memoryAdvisor))
                .defaultTools(timeTools)
                .build();
    }

    @Bean
    ToolExecutionExceptionProcessor toolExecutionException(){
        return new DefaultToolExecutionExceptionProcessor(true);
    }
}
