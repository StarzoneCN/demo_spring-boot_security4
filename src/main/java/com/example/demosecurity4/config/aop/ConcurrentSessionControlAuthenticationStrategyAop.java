package com.example.demosecurity4.config.aop;

import com.example.demosecurity4.config.listener.PassiveOfflineApplicationEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: LiHongxing
 * @email: lihongxing@bluemoon.com.cn
 * @date: Create in 2019/1/11 17:13
 * @modefied:
 */
@Aspect
@Component
public class ConcurrentSessionControlAuthenticationStrategyAop {
    @Autowired private ApplicationContext applicationContext;

    @Before("execution(void org.springframework.security.web.authentication.session" +
            ".ConcurrentSessionControlAuthenticationStrategy.onAuthentication(..)) && args(authentication, request, response)")
    // @After("@annotation(requestMapping)")
    public void interceptAllowableSessionsExceeded(final JoinPoint joinpoint, Authentication authentication,
                                                   HttpServletRequest request, HttpServletResponse response) {
        ConcurrentSessionControlAuthenticationStrategy authenticationStrategy =
                (ConcurrentSessionControlAuthenticationStrategy) joinpoint.getTarget();
        try {
            Field sessionRegistryField = ConcurrentSessionControlAuthenticationStrategy.class.getDeclaredField("sessionRegistry");
            sessionRegistryField.setAccessible(true);
            final SessionRegistry sessionRegistry = (SessionRegistry) sessionRegistryField.get(authenticationStrategy);
            final List<SessionInformation> sessions = sessionRegistry.getAllSessions(
                    authentication.getPrincipal(), false);

            int sessionCount = sessions.size();
            Field maximumSessionsField = ConcurrentSessionControlAuthenticationStrategy.class
                    .getDeclaredField("maximumSessions");
            maximumSessionsField.setAccessible(true);
            int allowedSessions = (int)maximumSessionsField.get(authenticationStrategy);

            if (sessionCount < allowedSessions) {
                // They haven't got too many login sessions running at present
                return;
            }

            if (allowedSessions == -1) {
                // We permit unlimited logins
                return;
            }

            if (sessionCount == allowedSessions) {
                HttpSession session = request.getSession(false);

                if (session != null) {
                    // Only permit it though if this request is associated with one of the
                    // already registered sessions
                    for (SessionInformation si : sessions) {
                        if (si.getSessionId().equals(session.getId())) {
                            return;
                        }
                    }
                }
                // If the session is null, a new one will be created by the parent class,
                // exceeding the allowed number
            }

            Field exceptionIfMaximumExceededField = ConcurrentSessionControlAuthenticationStrategy.class
                    .getDeclaredField("exceptionIfMaximumExceeded");
            exceptionIfMaximumExceededField.setAccessible(true);
            allowableSessionsExceeded(sessions,
                    (Boolean) exceptionIfMaximumExceededField.get(authenticationStrategy), request);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    protected void allowableSessionsExceeded(List<SessionInformation> sessions, boolean exceptionIfMaximumExceeded,
                                             HttpServletRequest request)
            throws SessionAuthenticationException {
        if (exceptionIfMaximumExceeded || (sessions == null)) {
            return;
        }

        // Determine least recently used session, and mark it for invalidation
        SessionInformation leastRecentlyUsed = null;

        for (SessionInformation session : sessions) {
            if ((leastRecentlyUsed == null)
                    || session.getLastRequest()
                    .before(leastRecentlyUsed.getLastRequest())) {
                leastRecentlyUsed = session;
            }
        }

        // leastRecentlyUsed.expireNow();
        applicationContext.publishEvent(new PassiveOfflineApplicationEvent(leastRecentlyUsed){{
            this.setMsgObject(new NewLoginInfo(){{
                this.setRemoteAdress(request.getRemoteAddr());
            }});
        }});
    }
}
