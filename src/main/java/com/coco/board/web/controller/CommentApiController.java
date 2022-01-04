package com.coco.board.web.controller;

import com.coco.board.config.auth.LoginUser;
import com.coco.board.service.CommentService;
import com.coco.board.web.dto.comment.CommentRequestDto;
import com.coco.board.web.dto.comment.CommentResponseDto;
import com.coco.board.web.dto.user.UserSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                                      @LoginUser UserSessionDto userSessionDto) {
        return ResponseEntity.ok(commentService.commentSave(userSessionDto.getNickname(), id, dto));
    }

    /* READ */
    @GetMapping("/posts/{id}/comments")
    public CommentResponseDto read(@PathVariable Long id) {
        return commentService.findById(id);
    }

}
