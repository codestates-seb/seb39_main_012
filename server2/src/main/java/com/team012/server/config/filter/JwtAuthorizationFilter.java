//package com.team012.server.config.filter;
//
//import com.nimbusds.jose.Algorithm;
//import com.nimbusds.jwt.JWT;
//import com.team012.server.config.oauth.PrincipalDetails;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//
////    private MemberRepository memberRepository;
//
////    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
////        super(authenticationManager);
////        this.memberRepository = memberRepository;
////    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
//
//        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//        String jwtToken = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");
//
//        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();
//
//        if (username != null) {
//            Member memberEntity = memberRepository.findByUsername(username);
//
//            PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            chain.doFilter(request, response);
//        }
//        else {
//            super.doFilterInternal(request, response, chain);
//        }
//    }
//}