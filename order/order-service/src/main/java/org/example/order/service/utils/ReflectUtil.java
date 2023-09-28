package org.example.order.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-11
 * @Description: 反射工具类
 */
@Slf4j
public class ReflectUtil {

    /**
     * @param source      源对象
     * @param targetClass 目标类
     * @return 返回目标类对象，异常则返回null
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        T target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            log.error("convert error, source:{}, targetClassL{}", source, target, e);
        }
        return target;
    }

    /**
     * @param source                源对象
     * @param targetClass           目标类
     * @param customFieldFillAction 自定义字段填充函数
     * @return 返回目标类对象，异常则返回null
     */
    public static <T> T convert(Object source, Class<T> targetClass, Consumer<T> customFieldFillAction) {
        T target = convert(source, targetClass);
        if (Objects.nonNull(target)) {
            customFieldFillAction.accept(target);
        }
        return target;
    }
}
