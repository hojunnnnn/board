package com.coco.board.web.controller.user;

import com.coco.board.config.auth.JoinValidator;
import com.coco.board.service.UserService;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Log4j2
public class UserController {

    private final UserService userService;

    private final JoinValidator joinValidator;

    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserRequestDto userDto, Errors errors, Model model) {
        /* 중복검사 */
/*        joinValidator.validate(userDto, errors);
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
        }*/

        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userDto", userDto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return "/user/user-join";
        }
        /* 중복검사 */
        userService.checkUsernameDuplicate(userDto);
        userService.checkNicknameDuplicate(userDto);
        userService.checkEmailDuplicate(userDto);

        userService.userJoin(userDto);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/user/user-login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
