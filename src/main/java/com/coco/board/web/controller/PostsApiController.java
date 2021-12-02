package com.coco.board.web.controller;

import com.coco.board.service.posts.PostsService;
import com.coco.board.web.dto.posts.PostsRequestDto;
import com.coco.board.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity write(@RequestBody PostsRequestDto dto) {
        return ResponseEntity.ok(postsService.write(dto));
    }

    /* READ */
    @GetMapping("/posts/{id}")
    public PostsResponseDto read(@PathVariable Long id) {
        return postsService.findById(id);
    }

    /* UPDATE */
    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        return ResponseEntity.ok(postsService.update(id, requestDto));
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
