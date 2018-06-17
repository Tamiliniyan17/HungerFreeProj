package com.iniyan.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory factory;
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<>();
	
	static {
		factory=new Configuration().configure("com/iniyan/cfgs/hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static Session getSession() {
		Session ses=null;
		if(threadLocal.get()==null) {
			ses=factory.openSession();
			threadLocal.set(ses);
		}
		return threadLocal.get();
	}
	
	public static void closeSession() {
		if(threadLocal.get()!=null) {
			threadLocal.get().close();
			threadLocal.remove();
		}	
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}

}
