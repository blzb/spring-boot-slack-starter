package com.blzb.bot.auth.service;

/**
 * Created by apimentel on 2/5/18.
 */
public interface AuthService {
    void auth(String code);

    boolean hasBotAuth();

    String getToken();

    String getBotUserId();
}
