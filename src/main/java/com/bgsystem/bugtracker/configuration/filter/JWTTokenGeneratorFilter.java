package com.bgsystem.bugtracker.configuration.filter;

import com.bgsystem.bugtracker.configuration.security.SecurityConstant;
import com.bgsystem.bugtracker.shared.models.user.User;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {


    private UserRepository userRepository;

    @Autowired
    public JWTTokenGeneratorFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<User> authUserList = userRepository.findByUsername(authentication.getName());

        User actualUser = authUserList.iterator().next();

        String usernameAuth = actualUser.getUsername();

        List <String> roles = new ArrayList<>(actualUser.getRoles());

        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstant.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("ProjectManager").setSubject("JWT Token")
                    .claim("username", usernameAuth)
                    .claim("authorities", roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 900000000))
                    .signWith(key).compact();
            response.setHeader(SecurityConstant.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
