package com.coco.board.web.controller;

import com.coco.board.service.posts.PostsService;
import com.coco.board.web.dto.PostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Long> write(@RequestBody PostsRequestDto dto) {
        Long id = postsService.write(dto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /* UPDATE */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(postsService.update(id, requestDto));
        }
    }

}
