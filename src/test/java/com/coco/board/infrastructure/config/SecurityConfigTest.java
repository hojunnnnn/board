package com.coco.board.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class SecurityConfigTest {

    @Test
    public void 해쉬_암호화() {
        String encodePassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 해쉬 : " + encodePassword);
    }
}