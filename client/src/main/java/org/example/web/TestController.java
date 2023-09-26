package org.example.web;

import cn.hutool.json.JSONUtil;
import io.micrometer.core.instrument.util.JsonUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-08
 * @Description:
 */
@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/test")
    public String test(){
        return "client test";
    }
    @GetMapping("/test2")
    public String tes2(){
        return "client ";
    }

    @Value(value = "${security.oauth2.client.client-id}")
    private String clientId;

    @Value(value = "${security.oauth2.client.client-secret}")
    private String clientSecret;

    @SneakyThrows
    @GetMapping("/getAccessToken")
    public String getAccessToken(String code){
        String url = "http://localhost:10010/oauth-server/oauth/token";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authorization", String.format(
                "Basic %s",
                new String(Base64.encode(String.format("%s:%s", clientId,
                        clientSecret).getBytes("UTF-8")), "UTF-8")));


        // 要发送的数据对象
        Map<String, String> body = new HashMap<>();
        body.put("client_id", "client-1");
        body.put("grant_type","authorization_code");

        body.put("code",code);
        HttpEntity<String> requestEntity = new HttpEntity<>(  JSONUtil.toJsonStr(body), requestHeaders);
        // 发送post请求，并输出结果
//        url = url + "?client_id?client-1&grant_type=authorization_code&code="+code;
//        Map<String, String> result = restTemplate.getForObject(url, Map.class);
        Map<String, String> result = restTemplate.postForObject(url, requestEntity, Map.class);
        System.out.println(result);
        return "client ";
    }
}
