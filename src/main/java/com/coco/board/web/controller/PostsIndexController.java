package com.coco.board.web.controller;

import com.coco.board.domain.posts.Posts;
import com.coco.board.domain.posts.PostsRepository;
import com.coco.board.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostsIndexController {

    private final PostsService postsService;

    private final PostsRepository postsRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/write")
    public String write() {
        return "posts-write";
    }

//    @GetMapping("/posts/read/{id}")
//    public String read(@PathVariable @RequestParam(required = false) Long id, Model model) {
//        if (id == null) {
//            model.addAttribute("posts", new Posts());
//        } else {
//            Posts posts = postsRepository.findById(id).orElse(null);
//            model.addAttribute("posts", posts);
//        }
//        return "posts-read";
//    }
}
