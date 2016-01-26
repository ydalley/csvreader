package com.ebsl.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.ebsl.service.OBCException;

public class ObcCredentailsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken arg0,
			AuthenticationInfo arg1) {
		
		char[] cred = (char[]) arg0.getCredentials();
		String pwd = String.valueOf(cred);
		
		
		SimpleAuthenticationInfo sai = (SimpleAuthenticationInfo)arg1;
		
//		cred = (char[]) arg1.getCredentials();
		String dbpwd = sai.getCredentials().toString();
		String adigest = null;
		try {
			adigest = generateSHAdigest(pwd);
		} catch (OBCException e) {
			e.printStackTrace();
		}
		return dbpwd.equals(adigest);
	}
	
	private  String generateSHAdigest(String plaintext)
			throws OBCException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new OBCException(e);
		}
		byte[] md_password = plaintext.getBytes();
		byte[] md_hash = md.digest(md_password);
		String bytes = "";
		for (byte by : md_hash) {
			bytes += String.format("%02x", by);
		}
		return bytes;
	}


}
