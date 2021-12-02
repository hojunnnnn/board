package com.coco.board.domain.comment;

import com.coco.board.domain.posts.Posts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

//    @BeforeEach
//    public void reset() {
//        commentRepository.deleteAll();
//    }
//
//    @AfterEach
//    public void clear() {
//        commentRepository.deleteAll();
//    }

    @Test
    public void 게시글_댓글_생성_조회() {
        String content = "댓글 입니다.";

        Posts posts = Posts.builder().id(22L).build();


            commentRepository.save(Comment.builder()
                    .content(content)
                    .writer("coco")
                    .posts(posts)
                    .build());

            List<Comment> comments = commentRepository.getCommentByPostsOrderById(posts);

            Comment comment = comments.get(0);

            assertThat(comment.getContent()).isEqualTo(content);
    }

    @Test
    public void 랜덤_댓글_생성() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            long id = (long)(Math.random() * 22) + 1;

            Posts posts = Posts.builder().id(id).build();

            Comment comment = Comment.builder()
                    .content(i + "번째 댓글입니다.")
                    .writer("coco" + i)
                    .posts(posts)
                    .build();

            commentRepository.save(comment);
        });
    }
}