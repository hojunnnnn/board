package com.coco.board.service;

import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    /* 회원가입 */
    @Transactional
    public Long userJoin(UserRequestDto dto) {

        dto.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(dto.toEntity()).getId();
    }

    /* 회원가입 시, 유효성 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 아이디, 닉네임, 이메일 중복 여부 확인 */
    @Transactional(readOnly = true)
    public void checkUsernameDuplicate(UserRequestDto dto) {
        boolean usernameDuplicate = userRepository.existsByUsername(dto.toEntity().getUsername());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkNicknameDuplicate(UserRequestDto dto) {
        boolean usernameDuplicate = userRepository.existsByNickname(dto.toEntity().getNickname());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkEmailDuplicate(UserRequestDto dto) {
        boolean usernameDuplicate = userRepository.existsByEmail(dto.toEntity().getEmail());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
}
