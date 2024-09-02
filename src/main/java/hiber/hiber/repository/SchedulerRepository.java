package hiber.hiber.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hiber.hiber.model.Scheduler;
import hiber.hiber.model.User;
import hiber.hiber.util.HibernateUtil;

public class SchedulerRepository {
    
    // Admin methods
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

    // Method to delete a scheduled bill by ID
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

    // User methods

    // Method to get scheduled bills for a specific user using userID
    public List<Scheduler> getSchedulersByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler WHERE userId = :userId";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get a scheduled bill for a user by bill name
    public List<Scheduler> getSchedulersByBillName(User user, String billName) {
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            String hql = "FROM Scheduler WHERE userId = :userId AND billName = :billName";
            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
            query.setParameter("userId", user);
            query.setParameter("billName", billName);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Adding a new scheduled bill
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

    // Method to update an existing scheduled bill
    public void updateScheduler(Scheduler scheduler) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
            transaction = session.beginTransaction();
            session.update(scheduler);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    // Printing all scheduled bills in the db
//    public void printAllScheduledPayments() {
//        try (Session session = HibernateUtil.getSessionFactoryObject().openSession()) {
//            String hql = "FROM Scheduler";
//            Query<Scheduler> query = session.createQuery(hql, Scheduler.class);
//            List<Scheduler> schedulers = query.list();
//
//            if (schedulers.isEmpty()) {
//                System.out.println("No scheduled payments found.");
//            } else {
//                for (Scheduler scheduler : schedulers) {
//                    System.out.println(scheduler);
//                    System.out.println("----------------------------");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
