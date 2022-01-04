package com.coco.board.service.posts;

import com.coco.board.domain.posts.PostsRepository;
import com.coco.board.domain.user.Role;
import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.service.PostsService;
import com.coco.board.web.dto.posts.PostsRequestDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
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

        PostsRequestDto posts = PostsRequestDto.builder()
                .title("Test Title")
                .writer(user.getNickname())
                .content("Test Content")
                .view(0)
                .user(user)
                .build();

        postsService.save(user.getNickname(), posts);

        log.info(posts);
    }
}