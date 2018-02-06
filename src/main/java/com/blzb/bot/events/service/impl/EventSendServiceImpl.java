package com.blzb.bot.events.service.impl;

import com.blzb.bot.auth.service.AuthService;
import com.blzb.bot.events.service.EventSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by apimentel on 2/6/18.
 */
public class EventSendServiceImpl implements EventSendService {

    @Autowired
    AuthService authService;

    @Override
    public void postMessage(String channel, String text) {
        if (authService.hasBotAuth()) {
            RestTemplate chatRestTemplate = new RestTemplate();
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("token", authService.getToken());
            parts.add("channel", channel);
            parts.add("text", text);
            chatRestTemplate.postForObject("https://slack.com/api/chat.postMessage", parts, String.class);
        }

    }
}
