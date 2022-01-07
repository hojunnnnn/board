package com.coco.board.web.controller;

import com.coco.board.config.auth.LoginUser;
import com.coco.board.web.dto.comment.CommentResponseDto;
import com.coco.board.web.dto.user.UserSessionDto;
import com.coco.board.domain.posts.Posts;
import com.coco.board.service.PostsService;
import com.coco.board.web.dto.posts.PostsResponseDto;
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
            Pageable pageable, @LoginUser UserSessionDto user) {
        Page<Posts> list = postsService.pageList(pageable);

        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("nextCheck", list.hasNext());
        model.addAttribute("preCheck", list.hasPrevious());

        return "index";
    }
    /* 글 작성 */
    @GetMapping("/posts/write")
    public String write(@LoginUser UserSessionDto user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "posts/posts-write";
    }

    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, @LoginUser UserSessionDto user, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        List<CommentResponseDto> comments = dto.getComments();

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
             * stream()의 anyMatch는 list를 순회하며 조건에 부합하는 객체가 한개라도 있으면 true를 리턴
             * allMatch는 모든 객체가 조건에 부합해야 true를 리턴한다.
             * 댓글 리스트를 뿌려주면서 부분적으로 true false를 줄 방법이 없을까
             * 엄청나게 고민해봤지만 방법이 없었다. 그래서 자바스크립트로 후처리를 해줬다.
             * mustache에는 조건문을 제공하지 않고 true/false 여부만 판단하기 때문에 항상 최종값만을 넘겨줘야한다.
             */
            if (comments.stream().anyMatch(s -> s.getUserId().equals(user.getId()))) {
                model.addAttribute("commentWriter", true);
            }
/*            for (int i = 0; i < comments.size(); i++) {
                boolean check = comments.get(i).getUserId().equals(user.getId());
                log.info(String.valueOf(check));
                if (check) {
                    model.addAttribute("commentWriter", true);
                } else {
                    model.addAttribute("commentWriter", false);
                }
            }*/
        }

        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return "posts/posts-read";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, @LoginUser UserSessionDto user, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("posts", dto);

        return "posts/posts-update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable, @LoginUser UserSessionDto user) {
        Page<Posts> searchList = postsService.search(keyword, pageable);

        if (user != null) {
            model.addAttribute("user", user);
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

