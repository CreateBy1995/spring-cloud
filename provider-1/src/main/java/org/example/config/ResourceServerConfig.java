//package org.example.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: dongcx
// * @CreateTime: 2023-09-08
// * @Description:
// */
//@Configuration
//public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {
//
//    @Value("${spring.application.name}")
//    private String RESOURCE_ID;
//    // 注册到授权中心的clientId
//    @Value("${spring.application.name}")
//    private String clientId;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(RESOURCE_ID)
//                //使用远程服务验证令牌服务
//                .tokenServices(tokenServices())
//                //无状态模式
//                .stateless(true);
//    }
//
//    /**
//     * 配置安全策略
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //路径匹配规则
//                .antMatchers("/oauth2/**")
//                //匹配scope
//                .access("#oauth2.hasScope('all')")
//                .and()
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    private ResourceServerTokenServices tokenServices() {
//        RemoteTokenServices services = new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl("http://localhost:10010/oauth/check_token");
//        services.setClientId(clientId);
//        services.setClientSecret("dcx123");
//        return services;
//    }
//}
