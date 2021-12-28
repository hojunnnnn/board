package com.coco.board.domain.user;

import com.coco.board.domain.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email", "nickname"})})
@Entity
public class User extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false)
    private String nickname;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* 회원정보 수정을 위한 set method*/
    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 동일한 값인 email만을 덮어씌워
     * 기존 데이터를 보존하도록 예외처리 */
    public User emailCover(String email) {
        this.email = email;
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}
