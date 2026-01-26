package com.ia.tools.controller;

import com.ia.tools.service.HelpDeskTicketService;
import com.ia.tools.tools.HelpDeskTickeTools;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/tools")
public class HelpDeskController {

    private final HelpDeskTicketService service;
    private final HelpDeskTickeTools helpDeskTickeTools;

    public HelpDeskController(HelpDeskTicketService service, HelpDeskTickeTools helpDeskTickeTools) {
        this.service = service;
        this.helpDeskTickeTools = helpDeskTickeTools;
    }

    @GetMapping("/help-desk")
    public ResponseEntity<String> helpDesk(@RequestHeader("username") String username,
                                           @RequestParam("message") String message) {
        String answer = service.hekpDeskChat(helpDeskTickeTools, message, username);
        return ResponseEntity.ok(answer);
    }
}
