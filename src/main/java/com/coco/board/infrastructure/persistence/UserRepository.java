package com.coco.board.infrastructure.persistence;

import com.coco.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /* Security */
    Optional<User> findByUsername(String username);

    /* OAuth */
    Optional<User> findByEmail(String email);

    /* user GET */
    User findByNickname(String nickname);

    /* 중복 검사> 중복인 경우 true, 중복되지 않은경우 false 리턴 */
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}
