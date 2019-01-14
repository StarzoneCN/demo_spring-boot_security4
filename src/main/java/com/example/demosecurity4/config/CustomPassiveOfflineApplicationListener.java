package com.example.demosecurity4.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * 监听所有ApplicationEvent
 * @author: LiHongxing
 * @email: lihongxing@bluemoon.com.cn
 * @date: Create in 2019/1/11 10:55
 * @modefied:
 */
@Component
public class CustomPassiveOfflineApplicationListener implements ApplicationListener<PassiveOfflineApplicationEvent> {

    @Override
    public void onApplicationEvent(PassiveOfflineApplicationEvent event) {
        System.out.println(((User)((SessionInformation)event.getSource()).getPrincipal()).getUsername() + " : 你被另外一个终端（"
                +  ((PassiveOfflineApplicationEvent.NewLoginInfo)event.getMsgObject()).getRemoteAdress()
                + "）登录挤下线了");
    }
}
