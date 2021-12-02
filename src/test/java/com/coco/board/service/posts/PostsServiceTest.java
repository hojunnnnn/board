package com.coco.board.service.posts;

import com.coco.board.domain.posts.PostsRepository;
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

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_생성() {
        PostsRequestDto posts = PostsRequestDto.builder()
                .title("Test Title")
                .writer("user")
                .content("Test Content")
                .view(0)
                .build();

        postsService.write(posts);

        log.info(posts);
    }
}