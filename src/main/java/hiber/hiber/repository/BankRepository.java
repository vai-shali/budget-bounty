package hiber.hiber.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hiber.hiber.model.Bank;
import hiber.hiber.util.HibernateUtil;

/**
 * Repository class for managing Bank entities.
 */
public class BankRepository {

	/**
	 * Retrieves all bank accounts from the database.
	 *
	 * @return a list of all Bank entities.
	 */
	public List<Bank> getAllBanks() {
		List<Bank> banks = new ArrayList<>();
		SessionFactory sf = HibernateUtil.getSessionFactoryObject();
		Session s = sf.openSession();
		Query qr = s.createQuery("FROM Bank");
		banks = qr.list();
		return banks;
	}

	/**
	 * Retrieves a bank account by its ID.
	 *
	 * @param bankId the ID of the bank to retrieve.
	 * @return the Bank entity with the specified ID, or null if not found.
	 */
	public Bank getBankById(int bankId) {
		SessionFactory sf = HibernateUtil.getSessionFactoryObject();
		Session s = sf.openSession();
		Query qr = s.createQuery("FROM Bank where bankId = :bankId");
		qr.setParameter("bankId", bankId);
		List<Bank> list = qr.list();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * Adds a new bank account to the database.
	 *
	 * @param bank the Bank entity to be added.
	 */
	public void addBank(Bank bank) {
		SessionFactory sf = HibernateUtil.getSessionFactoryObject();
		Session s = sf.openSession();
		try {
			Transaction tran = s.beginTransaction();
			s.save(bank);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	/**
	 * Updates an existing bank account in the database.
	 *
	 * @param bank the Bank entity to be updated.
	 */
	public void updateBank(Bank bank) {
		SessionFactory sf = HibernateUtil.getSessionFactoryObject();
		Session s = sf.openSession();
		try {
			Transaction tran = s.beginTransaction();
			s.update(bank);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	/**
	 * Deletes a bank account by its ID.
	 *
	 * @param bankId the ID of the bank to be deleted.
	 */
	public void deleteBank(int bankId) {
		SessionFactory sf = HibernateUtil.getSessionFactoryObject();
		Session s = sf.openSession();
		try {
			Transaction tran = s.beginTransaction();
			String hql = "DELETE FROM Bank WHERE bankId = :bankId";
			Query qr = s.createQuery(hql);
			qr.setParameter("bankId", bankId);

			qr.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
}
