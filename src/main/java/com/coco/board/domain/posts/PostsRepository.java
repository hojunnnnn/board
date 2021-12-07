package com.coco.board.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);

    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);

//    @Query("SELECT p, c FROM Posts p LEFT JOIN Comment c ON c.posts = p WHERE p.id = :id")
//    List<Object[]> getPostsWithComment(@Param("id") Long id);
//
//    /* 목록 화면에 필요한 데이터 */
//    @Query(value = "SELECT p, count(c) FROM Posts p LEFT JOIN Comment c ON c.posts = p GROUP BY p")
//    Page<Object[]> getPostsWithCommentCount(Pageable pageable);
//
//    @Query("SELECT p, count(c) FROM Posts p LEFT JOIN Comment c ON c.posts = p WHERE p.id = :id")
//    Object getPostsById(@Param("id") Long id);
}
