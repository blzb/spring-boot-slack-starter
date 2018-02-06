package com.blzb.bot.events.service.impl;

import com.blzb.bot.events.service.EventHandler;
import com.blzb.bot.events.service.EventTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

/**
 * Created by apimentel on 2/6/18.
 */
@Service
public class EventHandlerLogImpl implements EventHandler {
    @Override
    public EventTypeEnum getEventType() {
        return EventTypeEnum.MESSAGE;
    }

    @Override
    public void handleEvent(JsonNode payload) {
        System.out.println(payload);
    }

    @Override
    public long getExecutionOrder() {
        return 1;
    }
}
