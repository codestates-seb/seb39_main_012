package com.team012.server.config.filter;

public interface JwtProperties {
	String SECRET = "BE012"; // 우리 서버만 알고 있는 비밀값
	int EXPIRATION_TIME = 600000000; // 10000분

	int REFRESH_EXPIRATION_TIME = 60*1000*60*24;
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
