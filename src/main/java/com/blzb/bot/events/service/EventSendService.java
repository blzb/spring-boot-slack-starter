package com.blzb.bot.events.service;

/**
 * Created by apimentel on 2/6/18.
 */
public interface EventSendService {
    void postMessage(String channel, String text);

}
