package com.coco.board.domain.posts;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void createPostsAndGetPosts() {
        Posts posts = Posts.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .writer("작성자")
                .build();
        postsRepository.save(posts);

        List<Posts> postsList = postsRepository.findAll();
        log.info(postsList.get(0));
    }
}