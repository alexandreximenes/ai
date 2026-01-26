package com.ia.tools.controller;

import com.ia.tools.service.ToolsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1")
public class ToolsController {

    private final ToolsService toolsService;

    public ToolsController(ToolsService toolsService) {
        this.toolsService = toolsService;
    }

    @GetMapping("/tools/chat")
    public Flux<String> chat(@RequestParam String message,
                             @RequestHeader(value = "username", defaultValue = "anonymous")
                             String username){
        return toolsService.chat(message, username);
    }
}
