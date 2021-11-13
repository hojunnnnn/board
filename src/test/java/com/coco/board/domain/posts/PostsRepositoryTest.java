package com.coco.board.domain.posts;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


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
        String title = "제목 입니다.";
        String content = "내용 입니다";

        postsRepository.save(Posts.builder().title(title).content(content).writer("hojunnnnn").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

        log.info(posts);
    }
}