package com.iniyan.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.iniyan.utility.HibernateUtil;

@WebListener
public class SessionCloseListener implements ServletRequestListener {
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		if(((HttpServletRequest) sre.getServletRequest()).getServletPath().equals("/controller")){
		System.out.println("Session closed");
		HibernateUtil.closeSession();
		}
	}

}
