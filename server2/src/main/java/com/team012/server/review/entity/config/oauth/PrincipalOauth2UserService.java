//package com.team012.server.config.oauth;
//
//import com.team012.server.config.oauth.provider.FacebookUserInfo;
//import com.team012.server.config.oauth.provider.GoogleUserInfo;
//import com.team012.server.config.oauth.provider.NaverUserInfo;
//import com.team012.server.config.oauth.provider.OAuth2UserInfo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
//
//    private final MemberRepository memberRepository;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("userRequest: " + userRequest.getAccessToken());
//        System.out.println("userRequest: " + userRequest.getClientRegistration());
//
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        // 구글 로그인 버튼 클릭 -> 구글로그인창 -> 로그인 완료 -> code 를 리턴(OAuth-Client 라이브러리가 받아줌) -> AccessToken 요청하고 받음
//        // userRequest 정보 -> loadUser 함수 호출 -> 구글 회원 프로필을 받음
//        System.out.println("userRequest: " + super.loadUser(userRequest).getAttributes());
//
//
//        // 강제로 회원가입
//        OAuth2UserInfo oAuth2UserInfo = null;
//        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
//            System.out.println("구글 로그인 요청");
//            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
//            System.out.println("페이스북 로그인 요청");
//            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
//        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")){
//            System.out.println("네이버 로그인 요청");
//            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
//        } else {
//            System.out.println("우리는 구글과 페이스북과 네이버만 지원합니다.");
//        }
//
//
//        String provider = oAuth2UserInfo.getProvider(); // google
//        String providerId = oAuth2UserInfo.getProviderId();
//        String username = provider+"-"+providerId; // google_sub
//        String password = bCryptPasswordEncoder.encode("temporary");
//        String email = oAuth2UserInfo.getEmail();
//        String role = "ROLE_USER";
//
//
//        Member userEntity = memberRepository.findByUsername(username);
//        if ( userEntity == null) {
//            System.out.println("OAuth 첫 로그인입니다. ");
//            userEntity =Member.builder()
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .roles(role)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .build();
//            memberRepository.save(userEntity);
//        } else {
//            System.out.println("이미 회원가입되어 있습니다.");
//        }
//
//
//        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
//    }
//}