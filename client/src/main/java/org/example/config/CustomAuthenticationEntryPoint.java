package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-15
 * @Description:
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Value(value = "${security.oauth2.client.user-authorization-uri}")
    private String authorizationUri;
    @Value(value = "${security.oauth2.client.client-id}")
    private String clientId;

    @Value(value = "${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        String url = authorizationUri +
                "?redirect_uri=http://localhost:10030/oauth-client/login&response_type=code&client_id="+clientId;
        PrintWriter writer = response.getWriter();
        Map<String, Object> resultMap = new LinkedHashMap<>(4);
        resultMap.put("code", -1);
        resultMap.put("msg", "用户未登录或者token失效");
        resultMap.put("data", url);
        writer.write(new ObjectMapper().writeValueAsString(resultMap));
        writer.flush();
        writer.close();
    }
}
