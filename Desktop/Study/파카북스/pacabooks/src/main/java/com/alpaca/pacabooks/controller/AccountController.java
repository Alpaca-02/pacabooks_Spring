package com.alpaca.pacabooks.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface AccountController {

    public String addAcc(@RequestParam("id") String id,
                        @RequestParam("password") String pwd,
                        @RequestParam("email") String email,
                        @RequestParam("tel") String tel) throws Exception;


    public String loginAcc(@RequestParam("id") String id,
                           @RequestParam("password") String pwd,
                           Model model,
                           HttpServletRequest request) throws Exception;
}
