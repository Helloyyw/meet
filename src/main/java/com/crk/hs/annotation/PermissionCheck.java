package com.crk.hs.annotation;


import java.lang.annotation.*;

/**
 * 使用注解统一校验角色权限
 * @author zhanglf
 * @date 2019-04-29
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionCheck {
    //自定义角色值，如果是多个角色，用逗号分割。
    public String role() default "0";
}
