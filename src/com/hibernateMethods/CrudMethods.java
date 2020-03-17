package com.hibernateMethods;

import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
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
        int counter = 0;
        try {
            transaction = session.beginTransaction();
            String hql = "update User set";
            if (!user.getLogin().isEmpty()) {
                hql += " login =: login";
                counter++;
            }

            if (counter != 0) {
                hql += ", ";
            }

            if (!user.getPassword().isEmpty()) {
                hql += " password =: password";
                counter++;
            }

            if (counter != 0) {
                hql += " where userId =: userId";

                Query query = session.createQuery(hql);

                if (!user.getLogin().isEmpty()) query.setParameter("login", user.getLogin());
                if (!user.getPassword().isEmpty()) query.setParameter("password", user.getPassword());
                if (user.getUserId() != 0) query.setParameter("userId", user.getUserId());

                rc = query.executeUpdate();
                transaction.commit();
            }
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
        int counter = 0;
        try {
            transaction = session.beginTransaction();

            String hql = "update Customer set";
            if (!customer.getEmail().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " email =: email";
                counter++;
            }
            if (!customer.getPhoneNumber().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " phoneNumber =: phoneNumber";
                counter++;
            }
            if (!customer.getBankAccountNumber().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " bankAccountNumber =: bankAccountNumber";
                counter++;
            }
            if (customer.getLocationId() != -1) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " locationId =: locationId";
                counter++;
            }
            if (customer.getDateOfBirth() != null) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " dateOfBirth =: dateOfBirth";
                counter++;
            }

            if (counter != 0) {
                hql += " where customerId =: customerId";
                Query query = session.createQuery(hql);
                if (!customer.getEmail().isEmpty()) query.setParameter("email", customer.getEmail());
                if (!customer.getPhoneNumber().isEmpty()) query.setParameter("phoneNumber", customer.getPhoneNumber());
                if (!customer.getBankAccountNumber().isEmpty()) query.setParameter("bankAccountNumber", customer.getBankAccountNumber());
                if (customer.getLocationId() != -1) query.setParameter("locationId", customer.getLocationId());
                if (customer.getDateOfBirth() != null) query.setParameter("dateOfBirth", customer.getDateOfBirth());
                if (customer.getCustomerId() != 0) query.setParameter("customerId", customer.getCustomerId());

                rc = query.executeUpdate();
                transaction.commit();
            }

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

    public int carDataUpdate(Car car) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int rc = -1;
        int counter = 0;
        try {
            transaction = session.beginTransaction();

            String hql = "update Car set";
            if (!car.getManufacturer().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " manufacturer =: manufacturer";
                counter++;
            }
            if (!car.getModel().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " model =: model";
                counter++;
            }
            if (!car.getClazz().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " clazz =: clazz";
                counter++;
            }
            if (car.getNumberOfSeats() != -1) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " numberOfSeats =: numberOfSeats";
                counter++;
            }
            if (car.getNumberOfDoors() != -1) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " numberOfDoors =: numberOfDoors";
                counter++;
            }
            if (car.getTrunkCapacity() != -1) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " trunkCapacity =: trunkCapacity";
                counter++;
            }
            if (car.getDailyRentalCost() != -1) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " dailyRentalCost =: dailyRentalCost";
                counter++;
            }
            if (car.getProductionYear() != -1) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " productionYear =: productionYear";
                counter++;
            }
            if (!car.getColor().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " color =: color";
                counter++;
            }
            if (!car.getTransmission().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " transmission =: transmission";
                counter++;
            }
            if (counter != 0) hql += ",";
            hql += " archived =: archived";
            counter++;

            if (counter != 0) {
                hql += " where carId =: carId";
                Query query = session.createQuery(hql);
                if (!car.getManufacturer().isEmpty()) query.setParameter("manufacturer", car.getManufacturer());
                if (!car.getModel().isEmpty()) query.setParameter("model", car.getModel());
                if (!car.getClazz().isEmpty()) query.setParameter("clazz", car.getClazz());
                if (car.getNumberOfSeats() != -1) query.setParameter("numberOfSeats", car.getNumberOfSeats());
                if (car.getNumberOfDoors() != -1) query.setParameter("numberOfDoors", car.getNumberOfDoors());
                if (car.getTrunkCapacity() != -1) query.setParameter("trunkCapacity", car.getTrunkCapacity());
                if (car.getDailyRentalCost() != -1) query.setParameter("dailyRentalCost", car.getDailyRentalCost());
                if (car.getProductionYear() != -1) query.setParameter("productionYear", car.getProductionYear());
                if (!car.getColor().isEmpty()) query.setParameter("color", car.getColor());
                if (!car.getTransmission().isEmpty()) query.setParameter("transmission", car.getTransmission());
                query.setParameter("archived", car.isArchived());
                if (car.getCarId() != 0) query.setParameter("carId", car.getCarId());

                rc = query.executeUpdate();
                transaction.commit();
            }

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
        int counter = 0;
        try {
            transaction = session.beginTransaction();

            String hql = "update Location set ";
            if (!location.getStreetAddress().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " streetAddress =: streetAddress";
                counter++;
            }
            if (!location.getCity().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " city =: city";
                counter++;
            }
            if (!location.getPostalCode().isEmpty()) {
                if (counter != 0) {
                    hql += ",";
                }
                hql += " postalCode =: postalCode";
                counter++;
            }

            hql += " where locationId =: locationId";

            Query query = session.createQuery(hql);

            if (!location.getStreetAddress().isEmpty()) query.setParameter("streetAddress", location.getStreetAddress());
            if (!location.getCity().isEmpty()) query.setParameter("city", location.getCity());
            if (!location.getPostalCode().isEmpty()) query.setParameter("postalCode", location.getPostalCode());
            if (location.getLocationId() != -1) query.setParameter("locationId", location.getLocationId());

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
                    + "carId =: carId, "
                    + "startLocationId =: startLocationId, "
                    + "endLocationId =: endLocationId"
                    + " where rentalId =: rentalId");
            query.setParameter("cost", rental.getCost());
            query.setParameter("startRentalDate", rental.getStartRentalDate());
            query.setParameter("endRentalDate", rental.getEndRentalDate());
            query.setParameter("carId", rental.getCarId());
            query.setParameter("startLocationId", rental.getStartLocationId());
            query.setParameter("endLocationId", rental.getEndLocationId());
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

    public List<Rental> getRentalsFromUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Rental> rentals = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Rental r where r.customerId =: customerId");
            rentals = query.setParameter("customerId", user.getUserId()).list();
            if (rentals.isEmpty() || rentals == null) {
                return null;
            }
            else {
                return rentals;
            }
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
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
        User aux_user;
        int rc;
        if (user == null || customer == null || location == null) {
            rc = -1;
        }
        else {
            rc = createUser(user);
            if (rc == -1) return rc;
            rc = createLocation(location);
            if (rc == -1) return rc;
            aux_user = logIn(user.getLogin(), user.getPassword());
            customer.setCustomerId(aux_user.getUserId());
            customer.setLocationId(getLocationIdFromCity(location.getCity()));
            rc = createCustomer(customer);
        }
        return rc;
    }

    public boolean checkCarAvailability(Car car, Timestamp startRentalDate, Timestamp endRentalDate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Integer> rentals;

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

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("Select c from Car c");
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
            return null;
        } finally {
            session.close();
        }
    }

    public List<Rental> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Rental r");
            rentals = query.list();
            if (rentals.isEmpty() || rentals == null) {
                return null;
            }
            else {
                return rentals;
            }
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
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
