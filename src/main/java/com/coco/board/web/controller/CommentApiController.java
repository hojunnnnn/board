package com.coco.board.web.controller;

import com.coco.board.config.auth.LoginUser;
import com.coco.board.service.CommentService;
import com.coco.board.web.dto.comment.CommentRequestDto;
import com.coco.board.web.dto.comment.CommentResponseDto;
import com.coco.board.web.dto.user.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                                      @LoginUser UserSessionDto userSessionDto) {
        return ResponseEntity.ok(commentService.save(id, userSessionDto.getNickname(), dto));
    }

    /* READ */
    @GetMapping("/posts/{id}/comments")
    public CommentResponseDto read(@PathVariable Long id) {
        return commentService.findById(id);
    }

    /* UPDATE */
    @PutMapping({"/posts/{id}/comments/{id}"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        log.info("api controller id : " + id);
        commentService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}/comments/{id}")
    public ResponseEntity commentDelete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }

}
