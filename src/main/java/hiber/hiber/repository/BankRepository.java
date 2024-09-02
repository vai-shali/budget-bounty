package hiber.hiber.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hiber.hiber.model.Bank;
import hiber.hiber.util.HibernateUtil;

public class BankRepository {

    // Method to get all bank accounts
    public List<Bank> getAllBanks() {
    	List<Bank> banks = new ArrayList<>();
        SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		Query qr = s.createQuery("FROM Bank");
		banks=qr.list();
        return banks;
    }

    // Method to get a bank by bank ID
    public Bank getBankById(int bankId) {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		Query qr = s.createQuery("FROM Bank where bankId= :bankId");
		qr.setParameter("bankId", bankId);
		List<Bank> list=qr.list();
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
    }

    // Method to add a new bank account
    public void addBank(Bank bank) {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
		try {
			Transaction tran=s.beginTransaction();
			s.save(bank);
			tran.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
	        s.close();
	    }
    } 

    // Method to update an existing bank account
    public void updateBank(Bank bank) {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
        try {
    		Transaction tran=s.beginTransaction();
    		s.update(bank);
    		tran.commit();
        }
        catch(Exception e) {
        	e.printStackTrace();
        } finally {
            s.close();
        }
    }

    // Method to delete a bank account by ID
    public void deleteBank(int bankId) {
    	SessionFactory sf=HibernateUtil.getSessionFactoryObject();
		Session s=sf.openSession();
        try {
    		Transaction tran=s.beginTransaction();
    		String hql = "DELETE FROM Bank WHERE bankId = :bankId";
    		Query qr = s.createQuery(hql);
    		qr.setParameter("bankId", bankId);

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
