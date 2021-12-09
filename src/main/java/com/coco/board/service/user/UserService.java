package com.coco.board.service.user;

import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long register(UserRequestDto dto) {

        User user = dto.toEntity();

        userRepository.save(user);

        return user.getId();
    }
}
