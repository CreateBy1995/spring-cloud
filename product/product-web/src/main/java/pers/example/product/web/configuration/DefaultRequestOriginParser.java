package pers.example.product.web.configuration;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: dongcx
 * @CreateTime: 2024-09-03
 * @Description: 调用方解析
 */
@Component
public class DefaultRequestOriginParser implements RequestOriginParser {
    private final static String ORIGIN_KEY = "origin";

    private final static String DEFAULT_ORIGIN = "local_test";

    @Override
    public String parseOrigin(HttpServletRequest request) {
        String origin = request.getHeader(ORIGIN_KEY);
        return StringUtils.isBlank(origin) ? DEFAULT_ORIGIN : origin;
    }
}
