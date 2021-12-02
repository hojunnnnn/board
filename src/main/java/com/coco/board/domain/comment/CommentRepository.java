package com.coco.board.domain.comment;

import com.coco.board.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /* 게시글이 삭제되면 댓글도 같이 삭제 */
    @Modifying
    @Query("delete from Comment c where c.posts.id = :id")
    void deleteById(Long id);

    /* 게시글 댓글 목록 가져오기 */
    List<Comment> getCommentByPostsOrderById(Posts posts);
}
