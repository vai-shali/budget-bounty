package hiber.hiber.repository;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hiber.hiber.model.User;
import hiber.hiber.util.HibernateUtil;

public class UserRepository {
	public User findById(int userId) throws SQLException {
		SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		Query qr = s.createQuery("FROM User where userId= :userId");
		qr.setParameter("userId", userId);
		List<User> list=qr.list();
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
    }
	public List<User> findAll() throws SQLException {
		SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		Query qr = s.createQuery("FROM User");
		List<User> list=qr.list();
		if(list.size()==0) {
			return null;
		}
		return list;
    }
	public User findByEmail(String email) throws SQLException {
		SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		Query qr = s.createQuery("FROM User where email= :email");
		qr.setParameter("email", email);
		List<User> list=qr.list();
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
    }

    // Method to find a user by email and password
    public User findByEmailAndPassword(String email, String password) throws SQLException {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		Query qr = s.createQuery("FROM User where email= :email and password= :pass");
		qr.setParameter("email", email);
		qr.setParameter("pass", password);
		List<User> list=qr.list();
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
    }
    
    public void update(User user) throws SQLException {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
 		Session s=sf.openSession();
    	 try {
     		Transaction tran=s.beginTransaction();
     		String hql = "UPDATE User SET name = :name, username = :username, password = :password, " +
                     "phoneNumber = :phoneNumber, email = :email, role = :role " +
                     "WHERE userId = :userId";
     		Query qr = s.createQuery(hql);
			qr.setParameter("userId", user.getUserId());
			qr.setParameter("name", user.getName());
			qr.setParameter("username", user.getUsername());
			qr.setParameter("password", user.getPassword());
			qr.setParameter("phoneNumber", user.getPhoneNumber());
			qr.setParameter("email", user.getEmail());
			qr.setParameter("role",user.getRole());
			qr.executeUpdate();
     		tran.commit();
         }
         catch(Exception e) {
         	e.printStackTrace();
         } finally {
             s.close(); 
         }
    }
    
    public void save(User user) throws SQLException {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		try {
			Transaction tran=s.beginTransaction();
			String hql = "INSERT INTO User (userId, name, username, password, phoneNumber, email, role) " +
		             "SELECT :userId, :name, :username, :password, :phoneNumber, :email, :role";
			Query qr = s.createQuery(hql);
			qr.setParameter("userId", user.getUserId());
			qr.setParameter("name", user.getName());
			qr.setParameter("username", user.getUsername());
			qr.setParameter("password", user.getPassword());
			qr.setParameter("phoneNumber", user.getPhoneNumber());
			qr.setParameter("email", user.getEmail());
			qr.setParameter("role",user.getRole());
			qr.executeUpdate();
			tran.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
            s.close(); 
        }
    }
    
    public void delete(int userId) throws SQLException {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
    	try {
    		Transaction tran=s.beginTransaction();
    		String hql = "DELETE FROM User WHERE userId = :userId";
    		Query qr = s.createQuery(hql);
    		qr.setParameter("userId", userId);

    		qr.executeUpdate();
    		tran.commit();
        }
        catch(Exception e) {
        	e.printStackTrace();
        } finally {
            s.close(); 
        }
    }
}

