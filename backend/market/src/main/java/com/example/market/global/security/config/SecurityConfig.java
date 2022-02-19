package com.example.market.global.security.config;

import com.example.market.domain.user.repository.TokenRepository;
import com.example.market.domain.user.service.OAuthService;
import com.example.market.domain.user.service.UserDetailsServiceImpl;
import com.example.market.global.security.filter.JwtAuthenticationFilter;
import com.example.market.global.security.filter.JwtAuthorizeFilter;
import com.example.market.global.security.handler.OAuth2LoginSuccessHandler;
import com.example.market.global.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final OAuthService oAuthService;
    private final JwtTokenUtil jwtTokenUtil;
    private final OAuth2LoginSuccessHandler OAuth2LoginSuccessHandler;
    private final TokenRepository tokenRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());

        http
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/join").permitAll()
                .antMatchers("/test").authenticated()
                .antMatchers(HttpMethod.POST, "/api/products/wish/**").authenticated();


        http.oauth2Login()
                .successHandler(OAuth2LoginSuccessHandler)
                .loginPage("/login")
                .authorizationEndpoint()
                .baseUri("/login")
                .and()
                .userInfoEndpoint()
                .userService(oAuthService);


        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil, tokenRepository), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new JwtAuthorizeFilter(authenticationManager(), jwtTokenUtil, userDetailsService, tokenRepository), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

