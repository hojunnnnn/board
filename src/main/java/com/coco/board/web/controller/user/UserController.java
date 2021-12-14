package com.coco.board.web.controller.user;

import com.coco.board.service.UserService;
import com.coco.board.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserController {

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
