package com.ia.mcp_server.config;

import com.ia.mcp_server.tools.HelpDeskTickeTools;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MCPConfigServer {

    @Bean
    public List<ToolCallback> toolsCallbacks(HelpDeskTickeTools helpDeskTickeTools){
        return List.of(ToolCallbacks.from(helpDeskTickeTools));
    };
}
