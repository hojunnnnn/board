package com.coco.board.service;

import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.web.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Transactional
    public Long userJoin(UserDto dto) {

        dto.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(dto.toEntity()).getId();
    }
}
