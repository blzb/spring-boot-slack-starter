package com.blzb.bot.events.controller;

import com.blzb.bot.events.service.EventService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by apimentel on 2/5/18.
 */
@RestController
public class EventsController {
    @Autowired
    EventService messageService;

    @PostMapping(value = "/slack", produces = MediaType.TEXT_PLAIN_VALUE)
    public String slack(@RequestBody String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(body);

        JsonNode challengeNode = root.path("challenge");
        JsonNode typeNode = root.path("type");
        if (challengeNode != null && typeNode != null && "url_verification".equals(typeNode.asText())) {
            return challengeNode.asText();
        }

        messageService.handleMessage(root);
        return "Ok";
    }
}
