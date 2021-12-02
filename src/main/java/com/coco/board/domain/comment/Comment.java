package com.coco.board.domain.comment;

import com.coco.board.domain.TimeEntity;
import com.coco.board.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity

public class Comment extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "posts_id") // 외래 키 매핑을 위해 사용
    private Posts posts;
}

