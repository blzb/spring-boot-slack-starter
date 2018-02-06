package com.blzb.bot.auth.service.impl;

import com.blzb.bot.auth.AppCredentials;
import com.blzb.bot.auth.dao.AuthDao;
import com.blzb.bot.auth.dao.dos.BotAuthInfo;
import com.blzb.bot.auth.service.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apimentel on 2/5/18.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final String SLACK_URL = "https://slack.com/api/oauth.access?" +
            "client_id={client_id}&client_secret={client_secret}&code={code}";
    @Autowired
    private AuthDao authDao;

    @Autowired
    private AppCredentials appCredentials;

    @Override
    public void auth(String code) {
        Map<String, String> uriVars = new HashMap<>();
        uriVars.put("client_id", appCredentials.getClientId());
        uriVars.put("client_secret", appCredentials.getClientSecret());
        uriVars.put("code", code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(SLACK_URL, String.class, uriVars);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(responseEntity.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonNode botNode = root.path("bot");
        authDao.save(buildBotAuthInfo(botNode));
    }

    @Override
    public boolean hasBotAuth() {
        return authDao.getBotAuthInfo() != null;
    }

    @Override
    public String getToken() {
        BotAuthInfo authInfo = authDao.getBotAuthInfo();
        if (authInfo != null) {
            return authInfo.getAccessToken();
        } else {
            throw new UnsupportedOperationException("Missing access token");
        }
    }

    @Override
    public String getBotUserId() {
        BotAuthInfo authInfo = authDao.getBotAuthInfo();
        if (authInfo != null) {
            return authInfo.getId();
        } else {
            throw new UnsupportedOperationException("Missing access token");
        }
    }

    private BotAuthInfo buildBotAuthInfo(JsonNode botNode) {
        return new BotAuthInfo(botNode.path("bot_user_id").asText(), botNode.path("bot_access_token").asText());
    }
}
