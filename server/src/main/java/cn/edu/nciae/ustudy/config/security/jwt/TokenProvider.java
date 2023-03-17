package cn.edu.nciae.ustudy.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class TokenProvider implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final String base64Secret;
    private final long tokenValiditySeconds;
    private Key key;

    public TokenProvider(
            @Value("${ustudy.security.jwt.base64Secret}") String base64Secret,
            @Value("${ustudy.security.jwt.expireInSeconds}") long tokenValiditySeconds) {
        this.base64Secret = base64Secret;
        this.tokenValiditySeconds = tokenValiditySeconds;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyByte= Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyByte);
    }
    /**
     * 创建Token
     */
    public String create(Authentication authentication){
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValiditySeconds * 1000);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

    /**
     * 验证Token
     */
    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error("Invalid token.", e);
        }
        return false;
    }

    /**
     * 根据Token 获取认证对象
     */
    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parser().setSigningKey(this.key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities=
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User userDetail = new User(claims.getSubject(), StringUtils.EMPTY, authorities);
        return new UsernamePasswordAuthenticationToken(userDetail, token, authorities);
    }



}
