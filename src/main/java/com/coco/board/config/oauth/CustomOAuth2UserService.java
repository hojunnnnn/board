package com.coco.board.config.oauth;

import com.coco.board.domain.user.User;
import com.coco.board.domain.user.UserRepository;
import com.coco.board.web.dto.user.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * Security UserDetailsService == OAuth OAuth2UserService
 * */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /* OAuth2 서비스 id 구분코드 ( 구글, 카카오, 네이버 ) */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        log.info("registrationId : " + registrationId);

        /* OAuth2 로그인 진행시 키가 되는 필드 값 (PK) (구글의 기본 코드는 "sub") */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        log.info("============================================");
        log.info("userNameAttributeName : " + userNameAttributeName);

        /* OAuth2UserService */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info("=============================================");
        log.info("attribute.getUsername: " + attributes.getUsername() + " getEmail(): " + attributes.getEmail()
        + " getNickname(): " + attributes.getNickname() + " getAttributes(): " + attributes.getAttributes());
        log.info("=============================================");

        User user = saveOrCover(attributes);

        /* 세션 정보를 저장하는 직렬화된 dto 클래스 */
        session.setAttribute("user", new UserSessionDto(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleValue())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    /* 소셜로그인시 기존 회원이 존재하면 동일한 email만을 덮어씌워 기존의 데이터를 보존한다. */
    private User saveOrCover(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.emailCover(attributes.getEmail()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
