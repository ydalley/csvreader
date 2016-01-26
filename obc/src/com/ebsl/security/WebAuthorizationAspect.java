package com.ebsl.security;
import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebAuthorizationAspect {

    @Before("@target(org.springframework.stereotype.Controller) && @annotation(requiresRoles)")
    public void assertAuthorized(JoinPoint jp, RequiresRoles requiresRoles) {
        SecurityUtils.getSubject().checkRoles(Arrays.asList(requiresRoles.value()));//@RequiresPermissions
    }
    
    @Before("@target(org.springframework.stereotype.Controller) && @annotation(requiresPermissions)")
    public void assertAuthorized(JoinPoint jp, RequiresPermissions requiresPermissions) {
        SecurityUtils.getSubject().checkPermissions(requiresPermissions.value());
    }
}