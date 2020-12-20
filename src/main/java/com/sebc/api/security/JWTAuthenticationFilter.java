package com.sebc.api.security;

import com.sebc.api.entity.User;
import com.sebc.api.dao.UserDao;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author lijie
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private UserDao userDao;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDao userDao) {
        super(authenticationManager);
        this.userDao = userDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        //如果不包含Bearer则退出
        if (header != null && !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey("HSMyJwtSecret".getBytes())
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                String userName = user.split(":")[0];
                User currUser = Optional.ofNullable(userDao.findUserByUsername(userName)).orElse(null);
                if (currUser != null) {
                    return new UsernamePasswordAuthenticationToken(currUser, null, new ArrayList<>());
                }
                return new UsernamePasswordAuthenticationToken(new User(), null, new ArrayList<>());
            }
        }
        return null;
    }
}
