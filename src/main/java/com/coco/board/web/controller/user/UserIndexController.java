package com.coco.board.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserIndexController {

    @GetMapping("/user/join")
    public String join() {
        return "/user/user-join";
    }
}
