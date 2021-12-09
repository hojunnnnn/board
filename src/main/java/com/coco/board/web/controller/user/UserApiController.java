package com.coco.board.web.controller.user;

import com.coco.board.domain.user.User;
import com.coco.board.service.user.UserService;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    /* 회원가입 */
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.join(dto));
    }

    /* 로그인 */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDto dto, HttpSession session) {
        User user = userService.login(dto);

        if (user != null) {
            session.setAttribute("user", user);
        }
        return ResponseEntity.ok(user);
    }


}
