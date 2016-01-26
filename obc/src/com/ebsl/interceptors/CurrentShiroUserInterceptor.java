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

import java.util.Map;

import com.ebsl.actions.UserAware;
import com.ebsl.data.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Inserts the current Shiro user into the value stack so that it can be
 * injected into Struts 2 actions should they have a JavaBeans setter
 * <code>setShiroUser(org.apache.shiro.subject.Subject shiroUser)</code>.
 */
public class CurrentShiroUserInterceptor implements Interceptor
{
    private static transient final Logger LOGGER = LoggerFactory.getLogger(CurrentShiroUserInterceptor.class);

    public void destroy()
    {
    }

    public void init()
    {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
        Subject shiroUser = SecurityUtils.getSubject();
        Map session = actionInvocation.getInvocationContext().getSession();
        User user = (User) session.get( User.USER );
        LOGGER.debug("Adding {} to the value stack", shiroUser);

        actionInvocation.getStack().setValue("shiroUser", shiroUser);
        if (actionInvocation instanceof UserAware) {
	        ((UserAware)actionInvocation).setUser(user);
	        
	    }
	    
        return actionInvocation.invoke();
    }
}
