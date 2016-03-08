package net.cafe.security;


import java.util.Collection;
import java.util.List;

import net.cafe.data.model.Option;
import net.cafe.data.model.Profile;
import net.cafe.data.model.User;
import net.cafe.service.SecurityService;
import net.cafe.service.UserService;

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
import org.springframework.beans.factory.annotation.Autowired;


public class CafeInlineRealm extends AuthorizingRealm {

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
				user.getPassword(), ByteSource.Util.bytes(""), getName());
		return info;
	}

	
}
