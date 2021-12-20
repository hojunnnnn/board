package com.coco.board.validator;

import com.coco.board.domain.user.UserRepository;
import com.coco.board.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class UsernameCheckValidator extends AbstractValidator<UserRequestDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        if (userRepository.existsByUsername(dto.toEntity().getUsername())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
