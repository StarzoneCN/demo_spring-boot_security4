package com.example.demosecurity4.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * web工具类
 *
 * @author: LiHongxing
 * @email: lihongxing@bluemoon.com.cn
 * @date: Create in 2019/1/11 14:15
 * @modefied:
 */
public class WebUtils {
    private WebUtils(){

    }

    /**
     * 获取request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取session
     * @return HttpSession
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
}
