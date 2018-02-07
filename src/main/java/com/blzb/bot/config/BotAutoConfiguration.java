package com.blzb.bot.config;

import com.blzb.bot.auth.AppCredentials;
import com.blzb.bot.auth.controller.AuthController;
import com.blzb.bot.auth.dao.AuthDao;
import com.blzb.bot.auth.dao.impl.AuthDaoFileImpl;
import com.blzb.bot.auth.service.AuthService;
import com.blzb.bot.auth.service.impl.AuthServiceImpl;
import com.blzb.bot.events.controller.EventsController;
import com.blzb.bot.events.service.EventHandler;
import com.blzb.bot.events.service.EventReceiveService;
import com.blzb.bot.events.service.EventSendService;
import com.blzb.bot.events.service.impl.EventHandlerEchoImpl;
import com.blzb.bot.events.service.impl.EventReceiveServiceImpl;
import com.blzb.bot.events.service.impl.EventSendServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by apimentel on 2/6/18.
 */
@Configuration
public class BotAutoConfiguration {

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
    AuthService authService() {
        return new AuthServiceImpl();
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

    @Bean
    @ConditionalOnMissingBean
    AppCredentials appCredentials() {
        return new AppCredentials();
    }

    @Bean
    @ConditionalOnProperty(name = "bot.auth.endpoint.enabled", havingValue = "true", matchIfMissing = true)
    AuthController authController() {
        return new AuthController();
    }

    @Bean
    @ConditionalOnProperty(name = "bot.events.endpoint.enabled", havingValue = "true", matchIfMissing = true)
    EventsController eventsController() {
        return new EventsController();
    }
}
