package com.coco.board.config.auth;

import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원가입시 중복검사를 진행하는 Validator
 * */
@RequiredArgsConstructor
@Component
public class JoinValidator implements Validator {

    private final UserRepository userRepository;

    /* 인스턴스가 검증 대상 타입인지 확인 */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(User.class);
    }

    /* 검증 작업 */
    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto user = (UserRequestDto) target;

        if (userRepository.existsByUsername(user.getUsername())) {
            errors.rejectValue("username", "invalid.username",
                    new Object[]{user.getUsername()}, "이미 사용중인 아이디 입니다.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            errors.rejectValue("email", "invalid.email",
                    new Object[]{user.getEmail()}, "이미 사용중인 이메일 입니다.");
        }

        if (userRepository.existsByNickname(user.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname",
                    new Object[]{user.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }
    }
}