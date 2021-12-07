package com.coco.board.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저_생성_가져오기() {
        String username = "coco";
        String password = "1234";

        userRepository.save(User.builder().username(username).password(password).nickname("홍길동").email("coco@nate.com").build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);

        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
    }
}