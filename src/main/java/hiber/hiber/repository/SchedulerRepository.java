package hiber.hiber.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;
import hiber.hiber.util.HibernateUtil;

/**
 * Repository class for managing Scheduler entities.
 */
public class SchedulerRepository {

    // Admin methods

    /**
     * Retrieves all scheduled payments from the database.
     *
     * @return a list of all Scheduler entities.
     */
    public List<Scheduler> getAllScheduledPayments() {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to be deleted.
     */
    public void deleteScheduler(int schedulerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            transaction = session.beginTransaction();
            Scheduler scheduler = session.get(Scheduler.class, schedulerId);
            if (scheduler != null) {
                session.delete(scheduler);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a scheduled bill by its ID.
     *
     * @param schedulerId the ID of the scheduled bill to retrieve.
     * @return the Scheduler entity with the specified ID, or null if not found.
     */
    public Scheduler getSchedulerById(int schedulerId) {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler WHERE schedulerId = :schedulerId";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            query.setParameter("schedulerId", schedulerId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // User methods

    /**
     * Retrieves scheduled bills for a specific user based on their user ID.
     *
     * @param userId the ID of the user whose scheduled bills are to be retrieved.
     * @return a list of Scheduler entities associated with the specified user ID.
     */
    public List<Scheduler> getSchedulersByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler WHERE user.userId = :userId";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves scheduled bills for a user based on the bill name.
     *
     * @param user the User entity whose scheduled bills are to be retrieved.
     * @param billName the name of the bill to filter the scheduled bills.
     * @return a list of Scheduler entities associated with the specified user ID and bill name.
     */
    public List<Scheduler> getSchedulersByBillName(User user, String billName) {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler WHERE user.userId = :userId AND billName = :billName";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            query.setParameter("userId", user.getUserId());
            query.setParameter("billName", billName);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a new scheduled bill to the database.
     *
     * @param scheduler the Scheduler entity to be added.
     */
    public void addScheduler(Scheduler scheduler) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            transaction = session.beginTransaction();
            session.save(scheduler);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing scheduled bill in the database.
     *
     * @param scheduler the Scheduler entity to be updated.
     */
    public void updateScheduler(Scheduler scheduler) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            transaction = session.beginTransaction();
            session.update(scheduler);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    // Uncommented method for printing all scheduled payments
    /*
    // Method to print all scheduled bills in the database
    public void printAllScheduledPayments() {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            List<Scheduler> schedulers = query.list();

            if (schedulers.isEmpty()) {
                System.out.println("No scheduled payments found.");
            } else {
                for (Scheduler scheduler : schedulers) {
                    System.out.println(scheduler);
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
