package com.blzb.bot.config;

import com.blzb.bot.auth.dao.AuthDao;
import com.blzb.bot.auth.dao.impl.AuthDaoFileImpl;
import com.blzb.bot.events.service.EventHandler;
import com.blzb.bot.events.service.EventReceiveService;
import com.blzb.bot.events.service.EventSendService;
import com.blzb.bot.events.service.impl.EventHandlerEchoImpl;
import com.blzb.bot.events.service.impl.EventReceiveServiceImpl;
import com.blzb.bot.events.service.impl.EventSendServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by apimentel on 2/6/18.
 */
@Configuration
public class BotConfig {

    @Bean
    @ConditionalOnMissingBean
    AuthDao authDao() {
        return new AuthDaoFileImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    EventHandler eventHandler() {
        return new EventHandlerEchoImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    EventReceiveService eventService() {
        return new EventReceiveServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    EventSendService eventSendService() {
        return new EventSendServiceImpl();
    }
}
