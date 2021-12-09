package com.coco.board.web.dto.user;

import com.coco.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String username;

    private String password;

    private String nickname;

    private String email;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
        return user;
    }
}
