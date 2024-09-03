package hiber.hiber.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hiber.hiber.model.PaymentTransaction;
import hiber.hiber.util.HibernateUtil;

/**
 * Repository class for managing PaymentTransaction entities.
 * @author Vaishali
 * @since 2nd September, 2024
 */
public class PaymentTransactionRepository {

	/**
	 * Saves a PaymentTransaction entity to the database.
	 *
	 * @param paymentTransaction the PaymentTransaction entity to be saved.
	 */
	@SuppressWarnings("deprecation")
	public void save(PaymentTransaction paymentTransaction) {
		Session session = HibernateUtil.getSessionFactoryObject().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(paymentTransaction);  // Save the entity
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Finds a PaymentTransaction entity by its ID.
	 *
	 * @param transactionId the ID of the PaymentTransaction to find.
	 * @return the PaymentTransaction entity with the specified ID, or null if not found.
	 */
	public PaymentTransaction findById(Integer transactionId) {
		Session session = HibernateUtil.getSessionFactoryObject().openSession();
		PaymentTransaction paymentTransaction = null;
		try {
			paymentTransaction = session.get(PaymentTransaction.class, transactionId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return paymentTransaction;
	}

	/**
	 * Retrieves all PaymentTransaction entities from the database.
	 *
	 * @return a list of all PaymentTransaction entities.
	 */
	public List<PaymentTransaction> findAll() {
		Session session = HibernateUtil.getSessionFactoryObject().openSession();
		List<PaymentTransaction> paymentTransactions = null;
		try {
			Query<PaymentTransaction> query = session.createQuery("from PaymentTransaction", PaymentTransaction.class);
			paymentTransactions = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return paymentTransactions;
	}

	/**
	 * Updates an existing PaymentTransaction entity in the database.
	 *
	 * @param paymentTransaction the PaymentTransaction entity to be updated.
	 */
	public void update(PaymentTransaction paymentTransaction) {
		Session session = HibernateUtil.getSessionFactoryObject().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(paymentTransaction);  // Update the entity
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Deletes a PaymentTransaction entity by its ID.
	 *
	 * @param transactionId the ID of the PaymentTransaction to be deleted.
	 */
	public void delete(int transactionId) {
		Session session = HibernateUtil.getSessionFactoryObject().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			PaymentTransaction paymentTransaction = session.get(PaymentTransaction.class, transactionId);
			if (paymentTransaction != null) {
				session.delete(paymentTransaction);  // Delete the entity
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Retrieves PaymentTransaction entities based on the user ID.
	 *
	 * @param userId the ID of the user whose PaymentTransactions are to be retrieved.
	 * @return a list of PaymentTransaction entities associated with the specified user ID.
	 */
	public List<PaymentTransaction> findByUserId(int userId) {
		Session session = HibernateUtil.getSessionFactoryObject().openSession();
		List<PaymentTransaction> paymentTransactions = null;
		try {
			Query<PaymentTransaction> query = session.createQuery("from PaymentTransaction where user.userId = :userId", PaymentTransaction.class);
			query.setParameter("userId", userId);
			paymentTransactions = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return paymentTransactions;
	}
}
