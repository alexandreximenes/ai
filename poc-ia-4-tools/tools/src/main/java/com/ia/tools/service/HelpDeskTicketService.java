package com.ia.tools.service;

import com.ia.tools.entity.HelpDeskTicket;
import com.ia.tools.model.TicketRequest;
import com.ia.tools.repository.HelpDeskTicketRepository;
import com.ia.tools.tools.HelpDeskTickeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;


@Service
public class HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;
    private final ChatClient chatClient;

    public HelpDeskTicketService(HelpDeskTicketRepository helpDeskTicketRepository,
                                 @Qualifier("helpDeskChatClient") ChatClient chatClient) {
        this.helpDeskTicketRepository = helpDeskTicketRepository;
        this.chatClient = chatClient;
    }

    public HelpDeskTicket createTicket(TicketRequest ticketInput, String username) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
                .issue(ticketInput.issue())
                .username(username)
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }

    public String hekpDeskChat(HelpDeskTickeTools helpDeskTools, String message, String username) {
        return chatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, username))
                .user(message)
                .tools(helpDeskTools)
                .toolContext(Map.of("username", username))
                .call().content();
    }

    public void deleteByIdsUsername(List<HelpDeskTicket> tickets) {
        helpDeskTicketRepository.deleteAll(tickets);
    }
}
