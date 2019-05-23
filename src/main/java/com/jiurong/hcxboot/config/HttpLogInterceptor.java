package com.jiurong.hcxboot.config;

import com.jiurong.hcxboot.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Order(0)
@Slf4j
public class HttpLogInterceptor {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.jiurong.hcxboot..*Controller.*(..))")
    public void httpLog() {
    }

    @Before("httpLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("HTTP " + request.getMethod() + " " + request.getRequestURL().toString() + " {IP:" + IpUtils.getClientIpAddr(request) + "}");
    }

    @AfterReturning(returning = "ret", pointcut = "httpLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("HTTP EXECUTE TIME:" + (System.currentTimeMillis() - startTime.get()) + ", RESPONSE:" + ret);
        startTime.remove();
    }
}
