package com.alpaca.pacabooks.controller;

import com.alpaca.pacabooks.dao.AccountDAO;
import com.alpaca.pacabooks.service.AccountService;
import com.alpaca.pacabooks.service.AccountServiceImpl;
import com.alpaca.pacabooks.vo.AccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("accountController")
public class AccountControllerImpl implements AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountControllerImpl.class);

    @Autowired
    private AccountDAO accDAO;

    @Autowired
    private AccountService accountService;



    @PostMapping("/Acc/loginPro")
    public String loginPro(){

        return "index";
    }

    @RequestMapping("/join")

    public String joinPage(){
        return "join";
    }

    @PostMapping("/Acc/addAcc")
    public String addAcc(@RequestParam("id") String id,
                         @RequestParam("password") String pwd,
                         @RequestParam("email") String email,
                         @RequestParam("tel") String tel){
        accountService.addAcc(id, pwd, email, tel);

        return "index";
    }

    @RequestMapping("/MyPageReq")  // 마이페이지를 요청했을경우
    public String myPageLink(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        boolean login = false;




        if(session.getAttribute("loggedIn")!=null){  // 로그인 되어있을경우 -> 마이페이지로 이동

            return "redirect:/myPage";
        }else {     // 로그인 되어있지 않을경우 -> 로그인 페이지로 이동
            model.addAttribute("msg","로그인이 필요한 서비스입니다.");

            return "login";
        }

    }

    @RequestMapping("/myPage")
    public String myPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        AccountVO accountVO = (AccountVO)session.getAttribute("account"); // 세션에 저장된 유저정보

        model.addAttribute("accountInfo",accountVO);  // model에 유저정보를 담아 포워딩

        return "mypage";
    }

    @PostMapping("/Acc/login")
    public String loginAcc(@RequestParam("id") String id,
                           @RequestParam("password") String pwd,
                           Model model,
                           HttpServletRequest request){

        HttpSession session = request.getSession();
        AccountVO accVO = accountService.loginCheck(id, pwd);


        if(accVO!=null){  // 로그인한 계정 패스워드가 일치하는경우
            session.setAttribute("account",accVO);
            session.setAttribute("loggedIn",true);
            return "index";
        }else{
            model.addAttribute("msg", "아이디나 패스워드를 다시 확인해 주세요");
            return "login";
        }
    }

}




