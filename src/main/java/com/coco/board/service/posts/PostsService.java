package com.coco.board.service.posts;

import com.coco.board.domain.posts.Posts;
import com.coco.board.domain.posts.PostsRepository;
import com.coco.board.web.dto.PostsRequestDto;
import com.coco.board.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /* CREATE */
    @Transactional
    public Long write(PostsRequestDto dto) {

        Posts posts = dto.toEntity();

        postsRepository.save(posts);

        return posts.getId();
    }

    /* 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    /* READ */
    @Transactional(readOnly = true)
    public List<PostsResponseDto> read(Long id) {
        return postsRepository.findById(id).stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }
}

