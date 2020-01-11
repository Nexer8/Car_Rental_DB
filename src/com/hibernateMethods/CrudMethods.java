package com.hibernateMethods;

import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.hibernateUtils.HibernateUtils;

import com.car_rental.*;

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

    public int userDataUpdate(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int rc = -1;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update User set login =: login, "
                    + "password =: password"
                    + " where userId =: userId");
            query.setParameter("login", user.getLogin());
            query.setParameter("password", user.getPassword());
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

    public int customerDataUpdate(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int rc = -1;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Customer set email =: email, "
                    + "phoneNumber =: phoneNumber, "
                    + "bankAccountNumber =: bankAccountNumber"
                    + " where customerId =: customerId");
            query.setParameter("email", customer.getEmail());
            query.setParameter("phoneNumber", customer.getPhoneNumber());
            query.setParameter("bankAccountNumber", customer.getBankAccountNumber());
            query.setParameter("customerId", customer.getCustomerId());
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

    public int locationDataUpdate(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int rc = -1;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Location set streetAddress =: streetAddress, "
                    + "city =: city, "
                    + "postalCode =: postalCode"
                    + " where locationId =: locationId");
            query.setParameter("streetAddress", location.getStreetAddress());
            query.setParameter("city", location.getCity());
            query.setParameter("postalCode", location.getPostalCode());
            query.setParameter("locationId", location.getLocationId());

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

    public int rentalDataUpdate(Rental rental) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int rc = -1;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Rental set cost =: cost, "
                    + "startRentalDate =: startRentalDate, "
                    + "endRentalDate =: endRentalDate, "
                    + "carByCarId =: carId, "
                    + "locationByStartLocationId =: startLocationId, "
                    + "locationByEndLocationId =: endLocationId"
                    + " where rentalId =: rentalId");
            query.setParameter("cost", rental.getCost());
            query.setParameter("startRentalDate", rental.getStartRentalDate());
            query.setParameter("endRentalDate", rental.getEndRentalDate());
            query.setParameter("carId", rental.getCarByCarId());
            query.setParameter("startLocationId", rental.getLocationByStartLocationId());
            query.setParameter("endLocationId", rental.getLocationByEndLocationId());
            query.setParameter("rentalId", rental.getRentalId());

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

    public int createRental(Rental rental) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(rental);
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
            CriteriaQuery<Car> query = builder.createQuery(Car.class);
            Root<Car> root = query.from(Car.class);
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
