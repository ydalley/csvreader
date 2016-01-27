package com.ebsl.interceptors;

/**
 * Copyright 2010-2011 (C) The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.Method;
import java.util.List;

import com.ebsl.data.model.User;
import com.ebsl.service.SecurityService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Inserts the current Shiro user into the value stack so that it can be
 * injected into Struts 2 actions should they have a JavaBeans setter
 * <code>setShiroUser(org.apache.shiro.subject.Subject shiroUser)</code>.
 */

public class SecurityInterceptor implements Interceptor
{
    private static transient final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Autowired
    SecurityService securityservice;
    
    public void destroy()
    {
    }

    public void init()
    {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
    	Subject shiroUser = SecurityUtils.getSubject();
        LOGGER.debug("Getting {"+ shiroUser.getPrincipal() +"} from the value stack");
        String[] permission = getRequiredPermission(actionInvocation);
        try {
			shiroUser.checkPermissions(permission);
		} catch (UnauthorizedException e){
			Object action = actionInvocation.getAction ();
            if (action instanceof ActionSupport) {
            	List optionDesc = securityservice.getOptionDesc(permission);
            	User user = (User)shiroUser.getPrincipal();
            	String error = String.format("%s does not have permission to [%s]", user.getFirstName() +" "+user.getLastName(),optionDesc);
                ((ActionSupport) action).addActionError (error);
            }
			return Action.ERROR;
		}catch (UnauthenticatedException e){
			e.printStackTrace();
			return Action.LOGIN;
		}
		return actionInvocation.invoke();
    }
    
    private String[] getRequiredPermission(ActionInvocation invocation) {
		Object action = invocation.getAction();
		if (action != null) {
			Method method = null;
			try {
				method = action.getClass().getMethod(invocation.getProxy().getMethod());
				RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
				if(annotation != null){
					return annotation.value();
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return new String[]{};
	}
}
