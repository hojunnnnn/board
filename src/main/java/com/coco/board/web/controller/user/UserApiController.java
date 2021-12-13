package com.coco.board.web.controller.user;

import com.coco.board.service.UserService;
import com.coco.board.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    /* 회원가입 */
/*
    @PostMapping("/auth/join")
    public ResponseEntity join(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.userJoin(dto));
    }*/


    /* 로그인 */
/*    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDto dto, HttpSession session) {
        User user = userService.login(dto);

        if (user != null) {
            session.setAttribute("user", user);
        }
        return ResponseEntity.ok(user);
    }*/
}
