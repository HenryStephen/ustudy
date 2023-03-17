package cn.edu.nciae.ustudy.config.security;

import cn.edu.nciae.ustudy.config.security.captcha.CaptchaFilter;
import cn.edu.nciae.ustudy.config.security.jwt.JwtFilter;
import cn.edu.nciae.ustudy.config.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static cn.edu.nciae.ustudy.config.security.SecurityConstants.*;

/**
 * @author tang
 * @date 2020/9/7 14:48
 */
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CaptchaFilter captchaFilter;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable()

            .formLogin()
                .loginPage(URL_REQUIRE_LOGIN)
                .loginProcessingUrl(URL_TOKEN)
                .usernameParameter(PARAM_USERNAME)
                .passwordParameter(PARAM_PASSWORD)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()

            .headers().frameOptions().sameOrigin().and()

            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
                .antMatchers(
                        "/token/**",
                        "/code/**",
                        "/user/**",
                        "/course",
                        "/course/*/description",
                        "/course/*/schedule",
                        "/course/*/comment",
                        "/course/category/**"
                ).permitAll()
            .anyRequest().authenticated().and()

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(captchaFilter, JwtFilter.class)
            ;
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers(
                    "/**/*.html",
                    "/favicon.ico",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/v2/api-docs"
            );
        // @formatter:on
    }
}
