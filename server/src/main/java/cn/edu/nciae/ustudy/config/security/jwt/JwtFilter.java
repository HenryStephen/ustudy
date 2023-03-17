package cn.edu.nciae.ustudy.config.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter aims to validate authentication information in jwt.
 *
 * @author tang
 * @date 2020/9/7 20:20
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final TokenProvider tokenProvider;

    @Autowired
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws ServletException, IOException {

        String jwt = resolveToken(request);
        // TODO return validate result
        if (StringUtils.hasText(jwt) && tokenProvider.validate(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            logger.debug("No token request: {}", request.getRequestURI());
        }
        chain.doFilter(request, response);
    }

    /**
     * Get token from the request header.
     * Returns the string value after "Bearer"
     */
    private String resolveToken(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(header) && header.startsWith("Bearer")) {
            return header.substring(7);
        }
        return null;
    }


}
