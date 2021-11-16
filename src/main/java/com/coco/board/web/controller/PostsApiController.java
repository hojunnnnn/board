package com.coco.board.web.controller;

import com.coco.board.service.posts.PostsService;
import com.coco.board.web.dto.PostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    /* CREATE */
    @PostMapping("/posts")
    public ResponseEntity<Long> write(@RequestBody PostsRequestDto dto) {
        Long id = postsService.write(dto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

//    /* READ */
//    @GetMapping("/posts/{id}")
//    public ResponseEntity read(@PathVariable Long id, Model model) {
//        model.addAttribute("posts",postsService.read(id));
//        return new ResponseEntity<>(model, HttpStatus.OK);
//    }
}
