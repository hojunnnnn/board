package com.coco.board.web.controller;

import com.coco.board.config.auth.LoginUser;
import com.coco.board.web.dto.CommentDto;
import com.coco.board.web.dto.PostsDto;
import com.coco.board.web.dto.UserDto;
import com.coco.board.domain.posts.Posts;
import com.coco.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 화면 연결 Controller
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class PostsIndexController {

    private final PostsService postsService;

    @GetMapping("/")                 /* default page = 0, size = 10  */
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable, @LoginUser UserDto.UserSessionDto user) {
        Page<Posts> list = postsService.pageList(pageable);

        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "index";
    }
    /* 글 작성 */
    @GetMapping("/posts/write")
    public String write(@LoginUser UserDto.UserSessionDto user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "posts/posts-write";
    }

    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, @LoginUser UserDto.UserSessionDto user, Model model) {
        PostsDto.PostsResponseDto dto = postsService.findById(id);
        List<CommentDto.CommentResponseDto> comments = dto.getComments();

        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);

            log.info("comments : " + comments.toString());
            log.info("comments size : " + comments.size());
        }

        /* 사용자 관련 */
        if (user != null) {
            model.addAttribute("user", user);

            /* 게시글 작성자 본인인지 확인 */
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("writer", true);
            }

            /*댓글 작성자 본인인지 확인
             * mustache에는 조건문(Logic-less)을 제공하지 않고 true/false 여부만 판단한다.
             * 항상 최종값만을 넘겨받기 때문에 부분적으로 true false를 줄 방법이 없었다.
             * 그래서 자바스크립트로 후처리를 해줬다.
             */
/*            if (comments.stream().anyMatch(s -> s.getUserId().equals(user.getId()))) {
                model.addAttribute("isWriter", true);
            }*/

            for (int i = 0; i < comments.size(); i++) {
                boolean isWriter = comments.get(i).getUserId().equals(user.getId());
                log.info("isWriter? : " + isWriter);
                model.addAttribute("isWriter",isWriter);
            }
        }

        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return "posts/posts-read";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, @LoginUser UserDto.UserSessionDto user, Model model) {
        PostsDto.PostsResponseDto dto = postsService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("posts", dto);

        return "posts/posts-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable, @LoginUser UserDto.UserSessionDto user) {
        Page<Posts> searchList = postsService.search(keyword, pageable);

        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());

        return "posts/posts-search";
    }
}

