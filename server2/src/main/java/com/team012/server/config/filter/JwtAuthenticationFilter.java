//package com.team012.server.config.filter;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nimbusds.jose.Algorithm;
//import com.nimbusds.jwt.JWT;
//import com.team012.server.config.oauth.PrincipalDetails;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        try {
//            ObjectMapper om = new ObjectMapper();
//            Member member = om.readValue(request.getInputStream(), Member.class);
//
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
//
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//            return authentication;
//        } catch (IOException e) {
//            e.printStackTrace();;
//        }
//        return null;
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//
//        System.out.println("successfulAuthentication");
//        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
//
//        String jwtToken = JWT.create()
//                .withSubject(principalDetails.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
//                .withClaim("id", principalDetails.getMember().getId())
//                .withClaim("username", principalDetails.getMember().getUsername())
//                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
//    }
//}