package com.ia.mcp_server.service;

import com.ia.mcp_server.entity.HelpDeskTicket;
import com.ia.mcp_server.model.TicketRequest;
import com.ia.mcp_server.repository.HelpDeskTicketRepository;
import com.ia.mcp_server.tools.HelpDeskTickeTools;
import jakarta.transaction.Transactional;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public HelpDeskTicket getTicketsById(Long id) {
        return helpDeskTicketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ticket not found for id: " + id
                ));
    }

    public String chat(HelpDeskTickeTools helpDeskTools, String message, String username) {
        return chatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, username))
                .user(message)
                .tools(helpDeskTools)
                .toolContext(Map.of("username", username))
                .call().content();
    }

    @Transactional
    public void deleteByIdsUsername(String username) {
        helpDeskTicketRepository.deleteByUsername(username);
    }

    @Transactional
    public void deleteById(Long id) {
        helpDeskTicketRepository.deleteById(id);
    }
}
