package com.blzb.bot.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apimentel on 2/5/18.
 */
@Component
public class AppCredentials {
    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
