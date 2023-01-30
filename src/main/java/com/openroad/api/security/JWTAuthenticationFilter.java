package com.openroad.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openroad.api.data.DetailUserData;
import com.openroad.api.user.model.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    static final String TOKEN_SENHA = "0948fdd6-031c-4b38-8a25-222efe017760";

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Falha au autenticar usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {

        DetailUserData userData = (DetailUserData) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(userData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (30 * 24 * 60 * 60 * 100)))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();
    }

}
