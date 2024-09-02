package com.example.budget_bounty.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import com.example.budget_bounty.util.HibernateUtil;

import com.example.budget_bounty.model1.PaymentTransaction;

public class TransactionRepository {

	//saving a paymentTransaction into the db
	public void save(PaymentTransaction paymentTransaction) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;	//hibernate's transaction class
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

    //admin functions
    //find by paymentTransaction id
	public PaymentTransaction findById(Integer transactionId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    //get all paymentTransactions in the database
	public List<PaymentTransaction> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    // update paymentTransaction
	public void update(PaymentTransaction paymentTransaction) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
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


    // delete a paymentTransaction
	public void delete(int transactionId) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
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

    
    // user function: view their past paymentTransactions based on their user id
	public List<PaymentTransaction> findByUserId(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
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
    
	// Get the maximum transaction ID
    public int getMaxTransactionId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxId = null;
        try {
            Query<Integer> query = session.createQuery("select max(transactionId) from PaymentTransaction", Integer.class);
            maxId = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (maxId != null) ? maxId : 0; // Return 0 if no transactions are present
    }
}