package com.hibernateMethods;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.procedure.ProcedureCall;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.hibernateUtils.HibernateUtils;

import com.car_rental.*;
import org.hibernate.query.Query;

public class CrudMethods {

    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public int userStatusUpdate(User user, boolean status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int rc = -1;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update User set loginStatus =: status" + " where userId =: userId");
            query.setParameter("status", status);
            query.setParameter("userId", user.getUserId());
            rc = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return rc;
        } finally {
            session.close();
        }
        return rc;
    }
    /**
     * Search if the username exists in the DB through its password
     * @param login
     * @param password
     * @return 1 if the username was not found it or 0 if the username was found it
     */
    public User logIn(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> users = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where u.login =: login and u.password =: password");
            users = query.setParameter("login", login).setParameter("password", password).list();
            if (users.isEmpty() || users == null) {
                return null;
            }
            for (User user : users) {
                if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                    return user;
                }
            }
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return null;
    }

    public int createLocation(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
            return 0;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public int createUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return 0;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public int createCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return 0;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return -1;
        } finally {
            session.close();
        }
    }

    public int signUp(User user, Customer customer, Location location) {
        User aux_user = new User();
        if (user == null || customer == null || location == null) {
            return -1;
        }
        else {
            createUser(user);
            createLocation(location);
            aux_user = logIn(user.getLogin(), user.getPassword());
            customer.setCustomerId(aux_user.getUserId());
            createCustomer(customer);
        }
        return 0;
    }

    public List<Car> searchForCars(Car car, Location pickUpLoc, Location dropOffLoc) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Car> cars = null;
        CriteriaBuilder builder = session.getCriteriaBuilder();

        try {
            transaction = session.beginTransaction();
            CriteriaQuery<Car> crit = builder.createQuery(Car.class);
            crit.from(Car.class);

            cars = session.createQuery(crit).getResultList();
            if (cars.isEmpty() || cars == null) {
                return null;
            }
            return cars;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
