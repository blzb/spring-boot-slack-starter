package com.blzb.bot.events.service.impl;

import com.blzb.bot.auth.service.AuthService;
import com.blzb.bot.events.service.EventHandler;
import com.blzb.bot.events.service.EventSendService;
import com.blzb.bot.events.service.EventTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by apimentel on 2/6/18.
 */
public class EventHandlerEchoImpl implements EventHandler {

    @Autowired
    AuthService authService;

    @Autowired
    EventSendService eventSendService;

    @Override
    public EventTypeEnum getEventType() {
        return EventTypeEnum.MESSAGE;
    }

    @Override
    public void handleEvent(JsonNode payload) {
        if (authService.hasBotAuth() && !isEventFromBotUser(payload)) {
            echoText(payload);
        }
    }

    private boolean isEventFromBotUser(JsonNode payload) {
        JsonNode userNode = payload.get("event").get("user");
        String user = null;
        if (null != userNode) {
            user = userNode.asText();
        }
        return StringUtils.isEmpty(user) || user.equals(authService.getBotUserId());
    }

    private void echoText(JsonNode payload) {
        eventSendService.postMessage(
                payload.get("event").get("channel").asText(), payload.get("event").get("text").asText());
    }


    @Override
    public long getExecutionOrder() {
        return 10;
    }
}
