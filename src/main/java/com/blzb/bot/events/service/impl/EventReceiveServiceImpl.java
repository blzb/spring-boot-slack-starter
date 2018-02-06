package com.blzb.bot.events.service.impl;

import com.blzb.bot.events.service.EventHandler;
import com.blzb.bot.events.service.EventReceiveService;
import com.blzb.bot.events.service.EventTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by apimentel on 2/5/18.
 */
public class EventReceiveServiceImpl implements EventReceiveService {
    @Autowired
    List<EventHandler> eventHandlers;

    Map<EventTypeEnum, List<EventHandler>> handlers;

    @PostConstruct
    void init() {
        handlers = new HashMap<>();
        for (EventHandler handler : eventHandlers) {
            if (!handlers.containsKey(handler.getEventType())) {
                handlers.put(handler.getEventType(), new ArrayList<>());
            }
            handlers.get(handler.getEventType()).add(handler);
        }

        for (EventTypeEnum type : handlers.keySet()) {
            Collections.sort(handlers.get(type), Comparator.comparingLong(EventHandler::getExecutionOrder));
        }
    }

    @Override
    public void handleMessage(final JsonNode payload) {
        EventTypeEnum eventType = getEventType(payload);
        if (handlers.containsKey(eventType)) {
            for (EventHandler eventHandler : handlers.get(eventType)) {
                eventHandler.handleEvent(payload);
            }
        }
    }

    private EventTypeEnum getEventType(JsonNode payload) {
        return EventTypeEnum.fromJson(payload.get("event").get("type").asText());
    }
}
