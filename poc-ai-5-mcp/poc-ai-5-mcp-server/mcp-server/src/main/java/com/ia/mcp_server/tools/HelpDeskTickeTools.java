package com.ia.mcp_server.tools;

import com.ia.mcp_server.entity.HelpDeskTicket;
import com.ia.mcp_server.model.TicketRequest;
import com.ia.mcp_server.service.HelpDeskTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Component
public class HelpDeskTickeTools {

    Logger logger = LoggerFactory.getLogger(HelpDeskTickeTools.class);
    private final HelpDeskTicketService service;

    public HelpDeskTickeTools(@Lazy HelpDeskTicketService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Tool(
            name = "createTicket",
            description = "Create the support ticket"
//            returnDirect = true
    )
    public String createTicket(@ToolParam(description = "Details to create a Support ticket")
                               TicketRequest ticketRequest, ToolContext toolContext) {
        Object usernameContext = toolContext.getContext().get("username");
        String username = usernameContext != null ? (String) usernameContext : ticketRequest.username();
        logger.info("Creating support ticket for user: {} with details: {}", username, ticketRequest);
        HelpDeskTicket savedTicket = service.createTicket(ticketRequest, username);
        logger.info("Ticket created successfully. Ticket ID: {}, Username: {}", savedTicket.getId(), savedTicket.getUsername());
        return "Ticket #" + savedTicket.getId() + " created successfully for user " + savedTicket.getUsername();
    }

    @ResponseStatus(HttpStatus.OK)
    @Tool(name = "getTicketStatus",description = "Fetch the status of the tickets based on a given username")
    List<HelpDeskTicket> getTicketStatus(String username, ToolContext toolContext) {
        Object usernameContext = toolContext.getContext().get("username");
        String usernameTools = usernameContext != null ? (String) usernameContext : username;
        logger.info("Fetching tickets for user: {}", usernameTools);
        List<HelpDeskTicket> tickets =  service.getTicketsByUsername(usernameTools);
        logger.info("Found {} tickets for user: {}", tickets.size(), usernameTools);
//         throw new RuntimeException("Unable to fetch ticket status");
        return tickets;
    }

    @ResponseStatus(HttpStatus.OK)
    @Tool(name = "deleteTicketById", description = "delete Ticket by Id")
    public void deleteTicketById(Long id, ToolContext toolContext) {
        logger.info("DELETE: Fetching tickets for user: {}", id);
        HelpDeskTicket ticketsById = service.getTicketsById(id);
        logger.info("Found {} tickets for user: {}", ticketsById,  ticketsById.getUsername());
        service.deleteById(ticketsById.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @Tool(name = "deleteTicketByUsername", description = "delete Tickets by username")
    public void deleteTicketByUsername(String username, ToolContext toolContext) {
        logger.info("DELETE: Fetching tickets for user: {}", username);
        List<HelpDeskTicket> ticketsByUsername = service.getTicketsByUsername(username);
        logger.info("Found {} tickets for user: {}", ticketsByUsername.size(),  username);
        service.deleteByIdsUsername(username);
    }

}
