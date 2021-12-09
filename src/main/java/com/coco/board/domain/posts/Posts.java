package com.coco.board.domain.posts;

import com.coco.board.domain.TimeEntity;
import com.coco.board.domain.comment.Comment;
import com.coco.board.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Posts extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String writer;

    @Column(columnDefinition = "integer default 0")
    private int view;

    /* mappedBy로 연관관계의 주인이 아님을 명시 (DB에 FK 컬럼을 만들지 않게 설정)
     *  게시글에서 상세보기를 누르면 댓글을 보여줄 수 있게 LAZY로 수정
     *  임시로 EAGER */
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER)
    private List<Comment> comments;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "writer")
//    private User user;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}