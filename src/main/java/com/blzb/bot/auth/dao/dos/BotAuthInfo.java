package com.blzb.bot.auth.dao.dos;

import java.io.Serializable;

/**
 * Created by apimentel on 2/5/18.
 */
public class BotAuthInfo implements Serializable {
    private final String id;
    private final String accessToken;

    public BotAuthInfo(String id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
