package org.example.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-08
 * @Description:
 */
@Configuration
//@EnableOAuth2Sso
@EnableResourceServer
//@EnableOAuth2Client
public class SecurityConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test2","/getAccessToken").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
    }
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http   // 配置登录页并允许访问
//                .formLogin().permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/test2").permitAll()
//                .and()  .authorizeRequests().antMatchers("/test").permitAll()
//                // 其余所有请求全部需要鉴权认证
//                .anyRequest().authenticated()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().csrf().disable();
//    }

}