package com.blzb.bot.auth.controller;

import com.blzb.bot.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * Created by apimentel on 2/5/18.
 */
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/install")
    public ModelAndView install(Map<String, Object> model) {
        model.put("client_id", System.getenv("CLIENT_ID"));
        return new ModelAndView("install", model);
    }

    @GetMapping("/thanks")
    public String thanks(@RequestParam String code) throws IOException {
        authService.auth(code);
        return "thanks";
    }
}
