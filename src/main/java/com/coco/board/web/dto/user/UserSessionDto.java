package com.coco.board.web.dto.user;

import com.coco.board.domain.user.Role;
import com.coco.board.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
* 인증된 사용자 정보를 세션에 저장하기 위한 클래스
* 세션을 저장하기 위해 User 엔티티 클래스를 사용하게 되면 직렬화를 해야 하는데,
* 엔티티 클래스에 직렬화를 넣어주면 추후에 다른 엔티티와 연관관계를 맺을시
* 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려가 있음
* */
@Getter
public class UserSessionDto implements Serializable {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    /* Entity -> dto */
    public UserSessionDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
