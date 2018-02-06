package com.blzb.bot.auth.dao.impl;

import com.blzb.bot.auth.dao.AuthDao;
import com.blzb.bot.auth.dao.dos.BotAuthInfo;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by apimentel on 2/5/18.
 */
public class AuthDaoFileImpl implements AuthDao {
    private volatile BotAuthInfo botAuthInfo = null;

    @Override
    public void save(BotAuthInfo botAuthInfo) {
        writeToFile(botAuthInfo);
        this.botAuthInfo = botAuthInfo;
    }

    private void writeToFile(BotAuthInfo botAuthInfo) {
        try {
            FileOutputStream f = new FileOutputStream(new File("tokens.dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(botAuthInfo);
            o.close();
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BotAuthInfo getBotAuthInfo() {
        if (botAuthInfo == null) {
            botAuthInfo = readFromFile();
        }
        return botAuthInfo;
    }

    private BotAuthInfo readFromFile() {
        BotAuthInfo obj;
        try {
            FileInputStream fi = new FileInputStream(new File("tokens.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            obj = (BotAuthInfo) oi.readObject();
            oi.close();
            fi.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}
