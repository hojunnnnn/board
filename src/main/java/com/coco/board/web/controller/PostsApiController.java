package com.coco.board.web.controller;

import com.coco.board.config.auth.LoginUser;
import com.coco.board.service.PostsService;
import com.coco.board.web.dto.comment.CommentRequestDto;
import com.coco.board.web.dto.posts.PostsRequestDto;
import com.coco.board.web.dto.posts.PostsResponseDto;
import com.coco.board.web.dto.user.UserSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * REST API Controller
 */

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /* CREATE */
    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostsRequestDto dto, @LoginUser UserSessionDto userSessionDto) {
        return ResponseEntity.ok(postsService.save(userSessionDto.getNickname(), dto));
    }

    /* READ */
    @GetMapping("/posts/{id}")
    public PostsResponseDto read(@PathVariable Long id) {
        return postsService.findById(id);
    }

    /* UPDATE */
    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        postsService.update(id, requestDto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
