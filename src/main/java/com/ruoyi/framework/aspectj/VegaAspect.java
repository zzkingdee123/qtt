package com.ruoyi.framework.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VegaAspect {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	 // 配置织入点
    @Pointcut("@annotation(com.ruoyi.framework.aspectj.lang.annotation.Vega)")
    public void vegaPointCut()
    {
    }
    
    @Before("vegaPointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        handle(point);
    }

	private void handle(JoinPoint point) {
		
	}
}
