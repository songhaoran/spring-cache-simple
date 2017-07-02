package com.song.cache.cache.keyGenerator;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by Song on 2017/7/2.
 * 为了防止key值重复，这里自定义生成key的格式为：类名:参数名：参数值
 */
public class ClassAndMethodKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();

        String classSimpleName = target.getClass().getSimpleName();
        stringBuilder.append(classSimpleName + ":");

        for (int i = 0; i < params.length; i++) {
            stringBuilder.append(params[i].toString() + ":");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
