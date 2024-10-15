package com.example.vediosystem.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {
    /**
     * 字符串判空
     * @param str
     * @return
     */
    public boolean isEmpty(String str){
        if (str == null) return true;
        return str.trim().equals("");
    }
}
