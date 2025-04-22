package pers.example.product.web.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: dongcx
 * @CreateTime: 2024-09-03
 * @Description:
 */
@Slf4j
@Service
public class ResourceService {

    // 触发限流规则时会回调getBlock，如果getBlock没实现则降级调用getFailed
    // 业务方法抛出异常则调用getFailed
    @SentinelResource(value = "rule-a", fallback = "getFailed", blockHandler = "getBlock")
    public String getResource(Integer value){
        if (value > 0){
            throw new RuntimeException("get resource error");
        }
        return "resource success";
    }

    public String getFailed(Integer value){
        return "resource failed";
    }

    public String getBlock(Integer value, BlockException e){
        return "resource block";
    }
}
