package com.coco.board.web.dto.user;

import com.coco.board.domain.user.Role;
import com.coco.board.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
* 인증된 사용자 정보를 세션에 저장하기 위한 클래스
* User 엔티티 클래스를 직접 사용하지 않는다. ->
* 다른 엔티티와 연관관계를 맺고 있으면 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려
* */
@Getter
public class UserSessionDto implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    /* Entity -> dto */
    public UserSessionDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
