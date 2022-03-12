package com.coco.board.application.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 중복검사 유효성 검증을 위해 Validator를 구현한 추상 클래스
 */
@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (IllegalStateException e) {
            log.error("중복 검증 에러", e);
            throw e;
        }
    }
    /* 유효성 검증 로직 */
    protected abstract void doValidate(final T dto, final Errors errors);
}
