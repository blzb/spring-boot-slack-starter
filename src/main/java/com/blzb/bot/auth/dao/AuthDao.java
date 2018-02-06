package com.blzb.bot.auth.dao;

import com.blzb.bot.auth.dao.dos.BotAuthInfo;

/**
 * Created by apimentel on 2/5/18.
 */
public interface AuthDao {

    void save(BotAuthInfo botAuthInfo);

    BotAuthInfo getBotAuthInfo();

}
