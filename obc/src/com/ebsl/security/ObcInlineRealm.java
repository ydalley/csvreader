package com.ebsl.security;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ebsl.data.model.Option;
import com.ebsl.data.model.Profile;
import com.ebsl.data.model.User;
import com.ebsl.service.SecurityService;
import com.ebsl.service.UserService;

public class ObcInlineRealm extends AuthorizingRealm {

	@Autowired
	private UserService userservice;
	@Autowired
	private SecurityService securityservice;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		User user = (User)arg0.getPrimaryPrincipal() ;
//		User user = userservice.getUserForLoginByName(userName);
		Profile profile = securityservice.loadProfile(user.getProfile().getId());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(profile.getName());
		for (Option perm : profile.getOptions()) {
			info.addStringPermission(perm.getName());
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken at = (UsernamePasswordToken) arg0;
		SimpleAuthenticationInfo info = null;

		User user = userservice.getUserForLoginByName(at.getUsername());
		info = new SimpleAuthenticationInfo(user,
				user.getPassword(), ByteSource.Util.bytes(user.getLoginId()
						.toUpperCase()), getName());
		return info;
	}

	@Override
	protected void checkPermissions(Collection<Permission> arg0,
			AuthorizationInfo arg1) {
		// TODO Auto-generated method stub
		super.checkPermissions(arg0, arg1);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> arg0,
			AuthorizationInfo arg1) {
		// TODO Auto-generated method stub
		return super.isPermitted(arg0, arg1);
	}

	
	
}
