package com.ladecentro.util;

import com.ladecentro.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UtilFunction {

    @Autowired
    JwtUtil jwtUtil;

    public Long getUserIdFromToken(HttpServletRequest headers) {
        String token = headers.getHeader("x-auth-token");
        return jwtUtil.extractClaim(token, claims -> claims.get("user_id", Long.class));
    }

}
