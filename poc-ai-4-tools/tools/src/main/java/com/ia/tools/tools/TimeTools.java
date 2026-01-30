package com.ia.tools.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;

@Component
public class TimeTools {

    @Tool(
            name = "getCurrentLocalTime",
            description = "Get the current local time in HH:MM:SS format"
    )
    public String getCurrentLocalTime() {
        return LocalTime.now().toString();
    }
    Logger logger = LoggerFactory.getLogger(TimeTools.class);

    @Tool(
            name = "getCurrentTime",
            description = "Get the current time in specific time zone HH:MM:SS format"
    )
    public String getCurrentTime(
            @ToolParam(
            description = "Value representing the time zone") String timezone) {
        logger.info("Getting current time for timezone: {}", timezone);
        return LocalTime.now(ZoneId.of(timezone)).toString();
    }
}
