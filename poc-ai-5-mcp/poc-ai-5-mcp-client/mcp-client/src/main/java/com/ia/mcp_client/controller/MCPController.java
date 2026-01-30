package com.ia.mcp_client.controller;

import com.ia.mcp_client.service.MCPService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1")
public class MCPController {

    private final MCPService mcpService;

    public MCPController(MCPService mcpService) {
        this.mcpService = mcpService;
    }

    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam String message,
                             @RequestHeader(value = "username", defaultValue = "anonymous")
                             String username){
        return mcpService.chat(message, username);
    }
}
