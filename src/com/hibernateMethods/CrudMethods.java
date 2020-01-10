package com.hibernateMethods;

import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.hibernateUtils.HibernateUtils;

import com.car_rental.*;

public class CrudMethods {

    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    /**
     * Search if the username exists in the DB through its password
     * @param login
     * @param password
     * @return 1 if the username was not found it or 0 if the username was found it
     */
    public int logIn(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        User user = null;
        List<Object[]> rows = null;

        try {
            transaction = session.beginTransaction();
            var query = session.createQuery("Select login, password FROM User");
            rows = query.list();
            if (rows.isEmpty() || rows == null) {
                return 1;
            }
            for (Object[] row : rows) {
                if (login == row[0] && password == row[1]) {
                    return 0;
                }
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return 1;
        } finally {
            session.close();
        }

        return 0;

    }
}
