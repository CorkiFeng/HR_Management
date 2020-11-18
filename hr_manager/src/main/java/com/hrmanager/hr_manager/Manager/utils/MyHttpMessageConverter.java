package com.hrmanager.hr_manager.Manager.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


public class MyHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    /**
     * @Author  SupremeSir
     * @Description  重写父类中的 supports 方法，防止文件下载功能因 ResponseEntity<byte[]> 返回结果转换为字符串
     * 而导致的下载的文件无法正常打开或内容乱码
     * @Date  2019/7/9
     * @Param  [clazz]
     * @return  boolean
     **/
    @Override
    protected boolean supports(Class<?> clazz) {
        if (clazz.equals(byte[].class)) {
            return false;
        }
        return true;
    }
}