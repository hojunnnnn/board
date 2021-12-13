package com.coco.board.web.controller.user;

import com.coco.board.service.UserService;
import com.coco.board.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserIndexController {

    private final UserService userService;

    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    @PostMapping("/auth/joinProc")
    public String joinProc(UserDto userDto) {
        userService.userJoin(userDto);

        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/user/user-login"; }

/*    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }*/
}
