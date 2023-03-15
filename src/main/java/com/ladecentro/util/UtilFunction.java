package com.ladecentro.util;

import com.ladecentro.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UtilFunction {

    @Autowired
    JwtUtil jwtUtil;

    private static final String X_AUTH_TOKEN = "x-auth-token";
    private static final String USER_ID = "user_id";

    public String getUserIdFromToken(HttpServletRequest headers) {
        String token = headers.getHeader(X_AUTH_TOKEN);
        return jwtUtil.extractClaim(token, claims -> claims.get(USER_ID, String.class));
    }

}
