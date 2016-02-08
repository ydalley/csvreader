package com.ebsl.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsBean extends org.apache.commons.beanutils.BeanUtilsBean {
	@Override
	public void copyProperty(Object dest, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if (value == null)
			return;
		try {
			super.copyProperty(dest, name, value);
		} catch (IllegalArgumentException e) {
		}
	}
	
	private static BeanUtilsBean bean;
	
	public static BeanUtilsBean getInstance(){
		if(bean == null){
			bean = new BeanUtilsBean();
		}
		return bean;
	}

}
