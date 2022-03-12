package com.coco.board.application.validator;

import com.coco.board.infrastructure.persistence.UserRepository;
import com.coco.board.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 닉네임 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
 */
@RequiredArgsConstructor
@Component
public class NicknameCheckValidator extends AbstractValidator<UserDto.Request> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserDto.Request dto, Errors errors) {
        if (userRepository.existsByNickname(dto.toEntity().getNickname())) {
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
        }
    }
}
