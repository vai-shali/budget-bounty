package hiber.hiber.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	static SessionFactory sf=null;
	public static SessionFactory getSessionFactoryObject() {
		if(sf==null) {
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			sf=cfg.buildSessionFactory();
		}
		return sf;
	}
}
