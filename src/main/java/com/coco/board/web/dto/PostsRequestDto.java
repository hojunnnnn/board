package com.coco.board.web.dto;

import com.coco.board.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 게시글의 등록과 수정을 처리할 요청(Request) 클래스
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsRequestDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate, modifiedDate;
    private int view;

    /* Dto -> Entity */
    public Posts toEntity() {
        Posts posts = Posts.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .view(0)
                .build();
        return posts;
    }
}
