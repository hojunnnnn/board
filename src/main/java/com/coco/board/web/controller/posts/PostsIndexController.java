package com.coco.board.web.controller.posts;

import com.coco.board.config.auth.LoginUser;
import com.coco.board.web.dto.user.UserSessionDto;
import com.coco.board.domain.posts.Posts;
import com.coco.board.service.PostsService;
import com.coco.board.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 화면 연결 Controller
 */

@Controller
@RequiredArgsConstructor
public class PostsIndexController {

    private final PostsService postsService;

    @GetMapping("/")                 /* default page = 0, size = 10  */
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable, @LoginUser UserSessionDto user) {
        Page<Posts> list = postsService.pageList(pageable);

        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("nextCheck", list.hasNext());
        model.addAttribute("preCheck", list.hasPrevious());

        return "index";
    }

    @GetMapping("/posts/write")
    public String write(@LoginUser UserSessionDto user, Model model) {
        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }
        return "posts/posts-write";
    }

    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, @LoginUser UserSessionDto user, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }
        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);

        return "posts/posts-read";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, @LoginUser UserSessionDto user, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }
        model.addAttribute("posts", dto);

        return "posts/posts-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
           Pageable pageable, @LoginUser UserSessionDto user) {
        Page<Posts> searchList = postsService.search(keyword, pageable);

        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }
        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("nextCheck", searchList.hasNext());
        model.addAttribute("preCheck", searchList.hasPrevious());

        return "posts/posts-search";
    }
}
