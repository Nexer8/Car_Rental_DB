package com.hibernateMethods;

import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.ArrayList;
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
            query.setParameter("carId", rental.getCarId());
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

    public boolean checkCarAvailability(Car car, Timestamp startRentalDate, Timestamp endRentalDate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Integer> rentals = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select r.rentalId from Rental r where r.carId =: carId " +
                    "and r.startRentalDate <: endRentalDate and r.endRentalDate >: startRentalDate");
            query.setParameter("carId", car.getCarId());
            query.setParameter("endRentalDate", endRentalDate);
            query.setParameter("startRentalDate", startRentalDate);
            rentals = query.list();
            if (rentals.isEmpty() || rentals == null) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public int getLocationIdFromCity(String city) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Integer> locationIds = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("Select l.locationId from Location l where l.city =: city");
            query.setParameter("city", city);

            locationIds = query.list();
            if (locationIds.isEmpty() || locationIds == null || locationIds.size() > 1) {
                return -1;
            }
            else {
                return locationIds.get(0);
            }
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }

    public List<Car> checkCarSatisfyFilters(Car car) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Car> cars = null;

        try {
            transaction = session.beginTransaction();
            String hql = "from Car c " +
                    "where c.archived =: archived and c.locationId =: locationId";
            if (car.getModel() != null) hql += " and c.model =: model";
            if (car.getManufacturer() != null) hql += " and c.manufacturer =: manufacturer";
            if (car.getUserRating() != 0) hql += " and c.userRating >: userRating";
            if (car.getNumberOfSeats() != 0) hql += " and c.numberOfSeats =: numberOfSeats";
            if (car.getNumberOfDoors() != 0) hql += " and c.numberOfDoors =: numberOfDoors";

            Query query = session.createQuery(hql);

            if (car.getModel() != null) query.setParameter("model", car.getModel());
            if (car.getManufacturer() != null) query.setParameter("manufacturer", car.getManufacturer());
            if (car.getUserRating() != 0) query.setParameter("userRating", car.getUserRating());
            if (car.getNumberOfSeats() != 0) query.setParameter("numberOfSeats", car.getNumberOfSeats());
            if (car.getNumberOfDoors() != 0) query.setParameter("numberOfDoors", car.getNumberOfDoors());
            query.setParameter("archived", car.isArchived());
            query.setParameter("locationId", car.getLocationId());

            cars = query.list();
            if (cars.isEmpty() || cars == null) {
                return null;
            }
            else {
                return cars;
            }
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Car> searchForCars(Car car, Location pickUpLoc, Rental rental) {
        List<Car> entryCars = checkCarSatisfyFilters(car);
        List<Car> exitCars = new ArrayList<>();
        if (entryCars.isEmpty()) return null;

        for (Car auxCar : entryCars) {
            if (checkCarAvailability(auxCar, rental.getStartRentalDate(), rental.getEndRentalDate())) {
                exitCars.add(auxCar);
            }
        }
        return exitCars;
    }
}
