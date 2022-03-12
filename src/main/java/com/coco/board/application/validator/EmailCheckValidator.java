package com.coco.board.application.validator;

import com.coco.board.infrastructure.persistence.UserRepository;
import com.coco.board.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 이메일 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
 */
@RequiredArgsConstructor
@Component
public class EmailCheckValidator extends AbstractValidator<UserDto.Request> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserDto.Request dto, Errors errors) {
        if (userRepository.existsByEmail(dto.toEntity().getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}
