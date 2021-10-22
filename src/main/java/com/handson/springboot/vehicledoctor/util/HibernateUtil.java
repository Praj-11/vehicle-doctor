//package com.handson.springboot.vehicledoctor.util;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//
//public class HibernateUtil {
//
//	static SessionFactory sessionFactory;
//
//	public static SessionFactory getSessionFactory(){
//
//		if (sessionFactory == null){
//
//			Configuration configuration = new Configuration().configure("applicatin.properties");
//			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySetting(configuration.getProperties());
//			sessionFactory = configuration.buildSessionFactory(builder.build());
//		}
//
//		return sessionFactory;
//	}
//}
