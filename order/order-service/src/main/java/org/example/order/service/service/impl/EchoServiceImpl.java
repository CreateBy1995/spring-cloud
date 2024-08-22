package org.example.order.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.example.order.service.service.IEchoService;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-22
 * @Description:
 */
@Slf4j
@Service
public class EchoServiceImpl implements IEchoService {
    @Override
    public void echo(String message) {
        log.info("echo service print message : {}", message);
    }
}
