package com.example.demosecurity4.config;

import org.springframework.context.ApplicationEvent;

/**
 * 被动下线事件
 *
 * @author: LiHongxing
 * @email: lihongxing@bluemoon.com.cn
 * @date: Create in 2019/1/11 14:11
 * @modefied:
 */
public class PassiveOfflineApplicationEvent<T> extends ApplicationEvent {
    /**
     * 传递消息对象
     */
    private T msgObject;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PassiveOfflineApplicationEvent(Object source) {
        super(source);
    }

    public T getMsgObject() {
        return msgObject;
    }

    public void setMsgObject(T msgObject) {
        this.msgObject = msgObject;
    }

    public static class NewLoginInfo{
        private String remoteAdress;

        public String getRemoteAdress() {
            return remoteAdress;
        }

        public void setRemoteAdress(String remoteAdress) {
            this.remoteAdress = remoteAdress;
        }
    }
}
