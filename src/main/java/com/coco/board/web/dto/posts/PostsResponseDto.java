package com.coco.board.web.dto.posts;

import com.coco.board.domain.posts.Posts;
import com.coco.board.web.dto.comment.CommentResponseDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
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
    private Long userId;
    private List<CommentResponseDto> comments;

    /* Entity -> Dto*/
    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.content = posts.getContent();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
        this.view = posts.getView();
        this.userId = posts.getUser().getId();
        this.comments = posts.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        /*
        *  PostsIndexController에서 사용 > 무한참조 발생
        *  PostsService의 findById()메서드에서 PostsResponseDto()를 호출하면
        *  dto변환 생성자에서 posts.getComments() 메서드를 실행하여 comment 엔티티 클래스에 있는 user와 posts정보를 다 불러옴.
        *  ResponseDto에서 ManyToOne의 List타입을 어떻게 다룰지..
        * */
    }
}
