package com.coco.board.web.controller.user;

import com.coco.board.service.user.UserService;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity register(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

}
