package com.mnt.hikeapp.config.filter;

import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.util.Constants;
import com.mnt.hikeapp.util.JwtTokenUtil;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        final String token = header.split(" ")[1].trim();
        if (Strings.isEmpty(token)) throw new Error(Messages.TOKEN_NOT_FOUND);
        User user = userRepository
                .findByGoogleId(jwtTokenUtil.getGoogleIdForToken(token)).orElse(null);
        if (user == null) throw new Error(Messages.USERNAME_NOT_FOUND);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null,null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
