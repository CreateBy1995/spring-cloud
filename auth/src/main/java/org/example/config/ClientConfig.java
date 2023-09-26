package org.example.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-08
 * @Description:
 */
@Component
public class ClientConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private TokenStore tokenStore;
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc()
        // 定义了两个客户端应用的通行证
        clients.inMemory()// 使用in-memory存储
                .withClient("client-1")// client_id
                .secret(new BCryptPasswordEncoder().encode("dcx123"))// client_secret
                .authorizedGrantTypes("authorization_code", "refresh_token")// 该client允许的授权类型
                .scopes("all")// 允许的授权范围
                //  设置用户是否自动Approval操作, 默认值为 false,
                //  可选值包括 true,false, read,write.
                //  该字段只适用于grant_type="authorization_code的情况,当用户登录成功后,
                //  若该值为true或支持的scope值,则会跳过用户Approve的页面, 直接授权.
                .autoApprove(false)
                //加上验证回调地址
                .redirectUris("http://localhost:10030/oauth-client/login")
//                .redirectUris("http://localhost:10030/login")
                .and()
                .withClient("client-2")
                .secret(new BCryptPasswordEncoder().encode("dcx123"))
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://localhost:10031/oauth-client2/login");
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        //获取ClientDetailsService信息
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter);
        // 一天有效期
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));
        endpoints.tokenServices(tokenServices);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
//        security.tokenKeyAccess("isAuthenticated()");
        security
                //开启/oauth/token_key验证端口认证权限访问
                .tokenKeyAccess("isAuthenticated()")
                //开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

}
