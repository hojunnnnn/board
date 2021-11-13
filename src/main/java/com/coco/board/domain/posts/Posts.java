package com.coco.board.domain.posts;

import com.coco.board.domain.TimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@ToString
public class Posts extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String writer;

    @Builder
    public Posts(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}