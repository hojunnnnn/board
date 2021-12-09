package com.coco.board.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserIndexController {

    @GetMapping("/join")
    public String join() {
        return "/user/user-join";
    }

    @GetMapping("/login")
    public String login() { return "/user/user-login"; }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
