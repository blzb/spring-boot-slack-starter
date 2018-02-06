package com.blzb.bot.events.service;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by apimentel on 2/5/18.
 */
public interface EventReceiveService {
    void handleMessage(JsonNode payload);
}
