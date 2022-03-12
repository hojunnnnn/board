package com.coco.board.controller;

import com.coco.board.domain.Posts;
import com.coco.board.infrastructure.persistence.PostsRepository;
import com.coco.board.application.dto.PostsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @Autowired
    private PostsRepository postsRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_수정_테스트() {

        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .writer("writer")
                .content("content")
                .build());

        Long updateId = savePosts.getId();
        String changeTitle = "title2";
        String changeContent = "content2";

        PostsDto.Request requestDto = PostsDto.Request.builder()
                .title(changeTitle)
                .content(changeContent)
                .build();

        String url = "http://localhost:" + port + "/api/posts/" + updateId;

        HttpEntity<PostsDto.Request> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(changeTitle);
        assertThat(all.get(0).getContent()).isEqualTo(changeContent);
    }

}