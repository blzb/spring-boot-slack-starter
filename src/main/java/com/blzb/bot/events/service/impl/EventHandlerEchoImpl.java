package com.blzb.bot.events.service.impl;

import com.blzb.bot.auth.service.AuthService;
import com.blzb.bot.events.service.EventHandler;
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
@Service
public class EventHandlerEchoImpl implements EventHandler {

    @Autowired
    AuthService authService;

    @Override
    public EventTypeEnum getEventType() {
        return EventTypeEnum.MESSAGE;
    }

    @Override
    public void handleEvent(JsonNode payload) {
        System.out.println("Echo");
        if (authService.hasBotAuth() && !isEventFromBotUser(payload)) {
            echoText(payload);
        }
    }

    private boolean isEventFromBotUser(JsonNode payload) {
        JsonNode userNode = payload.get("event").get("user");
        String user = null;
        if(null != userNode){
            user= userNode.asText();
        }
        return StringUtils.isEmpty(user) || user.equals(authService.getBotUserId());
    }

    private void echoText(JsonNode payload) {
        postMessage(payload.get("event").get("channel").asText(), payload.get("event").get("text").asText());
    }

    private void postMessage(String channel, String text) {
        if (authService.hasBotAuth()) {
            RestTemplate chatRestTemplate = new RestTemplate();
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("token", authService.getToken());
            parts.add("channel", channel);
            parts.add("text", text);
            chatRestTemplate.postForObject("https://slack.com/api/chat.postMessage", parts, String.class);
        }
    }

    @Override
    public long getExecutionOrder() {
        return 10;
    }
}
