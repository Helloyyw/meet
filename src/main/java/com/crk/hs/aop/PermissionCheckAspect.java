package com.crk.hs.aop;

import com.alibaba.fastjson.JSONObject;
import com.crk.hs.annotation.PermissionCheck;
import com.crk.hs.entity.User;
import com.crk.hs.vo.CodeMsg;
import com.crk.hs.vo.ResultVo;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
/**
 * 角色权限校验-AOP
 * @author  zhanglf
 * @date 2019-04-29
 */
@Aspect
@Component
@Slf4j
public class PermissionCheckAspect {
    //切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
    @Pointcut("@annotation(com.crk.hs.annotation.PermissionCheck)")
    private void permissionCheckCut(){};

    //定义了切面的处理逻辑。即方法上加了@PermissionCheck
    @Around("permissionCheckCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("==========哈哈哈，进入AOP============================");
        //1.记录日志信息
        Signature signature = pjp.getSignature();
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        log.info("className:{},methodName:{}",className,methodName);

        //2.角色权限校验
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        if(targetMethod.isAnnotationPresent(PermissionCheck.class)){
            //获取方法上注解中表明的权限
            PermissionCheck permission = (PermissionCheck)targetMethod.getAnnotation(PermissionCheck.class);
            String role = permission.role();
            log.info("当前接口请求的用户角色role:{}",role);
            if(StringUtil.isNotEmpty(role)){
                String[] roles = role.split(",");//接口允许的角色
                List<String> list = Arrays.asList(roles);
                //如果该接口只允许老师角色访问。则要获取当前用户是不是老师角色。
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                Cookie[] cookies = request.getCookies();
                Cookie cookie = null;//设置一个为null的cookie
                String userInfo = null;
                 //循环cookies得到每个具体的cookie值
                     for (int j = 0; j < cookies.length; j++) {
                            cookie = cookies[j];
                            if (cookie.getName().equals("userinfo")) {
                                userInfo = cookie.getValue();
                            }
                        }
               String   userInfo1  =  URLDecoder.decode(userInfo, "UTF-8");
                User jsonObject = JSONObject.parseObject(userInfo1,User.class);
                String userRole = jsonObject.getAcl();//用户的角色
                if(list.contains(userRole)){
                    log.info("AOP权限角色校验通过，进入业务层处理！");
                    //3.执行业务逻辑，放行
                    return pjp.proceed();
                }else{
                    //如果没有权限,抛出异常,由Spring框架捕获,跳转到错误页面
                    return  ResultVo.error(CodeMsg.NO_ACL_Error);
                }
            }
        }
        return  ResultVo.error(CodeMsg.NO_ACL_Error);
    }
}
