package com.car_rental;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    private int userId;
    private String login;
    private String password;
    private boolean admin;
    private boolean loginStatus;
    private Customer customerByUserId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "admin")
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "login_status")
    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                admin == user.admin &&
                loginStatus == user.loginStatus &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, admin, loginStatus);
    }

    @OneToOne(mappedBy = "customerByUserId")
    public Customer getCustomerByUserId() {
        return customerByUserId;
    }

    public void setCustomerByUserId(Customer customerByUserId) {
        this.customerByUserId = customerByUserId;
    }
}
