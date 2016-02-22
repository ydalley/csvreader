package net.cafe.struts.interceptors;

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

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import net.cafe.data.model.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CurrentUserInterceptor implements Interceptor
{
    private static transient final Logger LOGGER = LoggerFactory.getLogger(CurrentUserInterceptor.class);

    @Override
	public void destroy()
    {
    }

    @Override
	public void init()
    {
    }

    @Override
	public String intercept(ActionInvocation actionInvocation) throws Exception
    {
    	try {
			Subject shiroUser = SecurityUtils.getSubject();
			User user = (User) shiroUser.getPrincipal() ;
			LOGGER.debug("Getting {"+ user+"} ");
			Object action = actionInvocation.getAction();
			actionInvocation.getStack().set("loggedOnUser", user);
//			if (action instanceof UserAware) {
//			    ((UserAware)action).setCurrentUser(user);
//			    
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
        return actionInvocation.invoke();
    }
}
