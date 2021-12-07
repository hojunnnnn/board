package com.coco.board.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/join")
    public String join() {
        return "/user/user-join";
    }
}
