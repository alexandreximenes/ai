package com.ia.tools.tools;

import com.ia.tools.entity.HelpDeskTicket;
import com.ia.tools.model.TicketRequest;
import com.ia.tools.service.HelpDeskTicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpDeskTickeTools {

    Logger logger = LoggerFactory.getLogger(HelpDeskTickeTools.class);
    private final HelpDeskTicketService service;

    @Tool(
            name = "createTicket",
            description = "Create the support ticket",
            returnDirect = true
    )
    public String createTicket(@ToolParam(description = "Details to create a Support ticket")
                               TicketRequest ticketRequest, ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        logger.info("Creating support ticket for user: {} with details: {}", username, ticketRequest);
        HelpDeskTicket savedTicket = service.createTicket(ticketRequest, username);
        logger.info("Ticket created successfully. Ticket ID: {}, Username: {}", savedTicket.getId(), savedTicket.getUsername());
        return "Ticket #" + savedTicket.getId() + " created successfully for user " + savedTicket.getUsername();
    }

    @Tool(name = "getTicketStatus",description = "Fetch the status of the tickets based on a given username")
    List<HelpDeskTicket> getTicketStatus(ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        logger.info("Fetching tickets for user: {}", username);
        List<HelpDeskTicket> tickets =  service.getTicketsByUsername(username);
        logger.info("Found {} tickets for user: {}", tickets.size(), username);
//         throw new RuntimeException("Unable to fetch ticket status");
        return tickets;
    }

    @Tool(name = "deleteDuplicatedTicket", description = "delete duplicate ticket")
    List<HelpDeskTicket> deleteTicketStatus(ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        logger.info("DELETE: Fetching tickets for user: {}", username);
        List<HelpDeskTicket> tickets =  service.getTicketsByUsername(username);
        logger.info("Found {} tickets for user: {}", tickets.size(), username);
        service.deleteByIdsUsername(tickets);
        return tickets;
    }

}
