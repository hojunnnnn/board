package com.coco.board.web.controller;

import com.coco.board.domain.posts.Posts;
import com.coco.board.domain.posts.PostsRepository;
import com.coco.board.service.posts.PostsService;
import com.coco.board.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  화면 연결 Controller
 */

@Controller
@RequiredArgsConstructor
public class PostsIndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }


    @GetMapping("/posts/write")
    public String write() {
        return "posts-write";
    }

    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-read";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }
}
