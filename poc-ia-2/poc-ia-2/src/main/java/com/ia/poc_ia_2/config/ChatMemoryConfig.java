package com.ia.poc_ia_2.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatMemoryConfig {

    @Bean
    ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(10)
                .build();
    }

    ChatOptions chatOptions = ChatOptions.builder()
            .temperature(0.7)                       //Controla a criatividade das respostas geradas
            .topP(0.9)                              //Controla a diversidade das respostas geradas
            .presencePenalty(0.0)                   //Evita que o modelo mude de assunto com muita frequência
            .frequencyPenalty(0.3)                  //Evita repetição excessiva de palavras ou frases
//            .maxTokens(350)                         //Define o tamanho máximo da resposta gerada
            .stopSequences(List.of("###", "FIM"))   //Define sequências que indicam o fim da resposta
            .build();

    @Bean("chatMemoryClient")
    public ChatClient chatMemoryClient(OllamaChatModel ollamaChatModel, ChatMemory chatMemory) {
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(ollamaChatModel)
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), memoryAdvisor))
                .build();
    }
}
