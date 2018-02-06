package com.blzb.bot.auth.dao.impl;

import com.blzb.bot.auth.dao.AuthDao;
import com.blzb.bot.auth.dao.dos.BotAuthInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apimentel on 2/5/18.
 */
public class AuthDaoInMemoryImpl implements AuthDao {

    private final Map<String, Object> map = new HashMap<>();

    @Override
    public void save(BotAuthInfo botAuthInfo) {
        map.put("bot-info", botAuthInfo);
    }

    @Override
    public BotAuthInfo getBotAuthInfo() {
        return (BotAuthInfo) map.getOrDefault("bot-info", new BotAuthInfo("", ""));
    }
}
