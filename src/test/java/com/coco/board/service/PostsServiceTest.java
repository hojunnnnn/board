package com.coco.board.service;

import com.coco.board.domain.posts.PostsRepository;
import com.coco.board.domain.user.Role;
import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.dto.PostsDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_생성() {
        User user = User.builder().username("coco").nickname("coco").email("coco@coco.co").role(Role.USER).build();

        PostsDto.Request posts = PostsDto.Request.builder()
                .title("Test Title")
                .writer(user.getNickname())
                .content("Test Content")
                .view(0)
                .user(user)
                .build();

        postsService.save(posts, user.getNickname());

        log.info(String.valueOf(posts));
    }
}