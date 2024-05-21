package com.bgsystem.bugtracker.configuration.filter;

import com.bgsystem.bugtracker.configuration.security.SecurityConstant;
import com.bgsystem.bugtracker.shared.models.securityUser.SecurityUserServiceImplements;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    private final SecurityUserServiceImplements userRepository;

    @Autowired
    public JWTTokenValidatorFilter(SecurityUserServiceImplements userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String initJwt = request.getHeader(SecurityConstant.JWT_HEADER);

        String jwt = "";

        if (!initJwt.isEmpty()){
            jwt = initJwt.substring(SecurityConstant.TOKEN_PREFIX.length());
        }

        if (!jwt.equals("")) {
            try {
                SecretKey key = Keys.hmacShaKeyFor(
                        SecurityConstant.JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
                String username = String.valueOf(claims.get("username"));

                UserDetails authUser = userRepository.loadUserByUsername(username);

                Authentication auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(),null, authUser.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);

            }catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received!");
            }

        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().startsWith("/login");
    }
}
