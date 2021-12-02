package com.coco.board.web.dto.posts;

import com.coco.board.domain.posts.Posts;
import lombok.*;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsResponseDto {

    private Long id;

    private String title;

    private String writer;

    private String content;

    private String createdDate, modifiedDate;

    private int view;

    /* Entity -> Dto*/
    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.content = posts.getContent();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
        this.view = posts.getView();
    }

}
