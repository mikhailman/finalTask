package by.verishko.kefir.entity;

import java.time.LocalDate;
import java.util.Objects;

public class User extends Entity {
    private int role;
    private String login;
    private String password;
    private String email;
    private long phone;
    private String name;
    private String surname;
    private boolean status;
    private LocalDate date_registration;

    public User() {
    }

    public User(int role, String login, String password, String email, long phone, String name, String surname, boolean status, LocalDate date_registration) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.date_registration = date_registration;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDate_registration() {
        return date_registration;
    }

    public void setDate_registration(LocalDate date_registration) {
        this.date_registration = date_registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return role == user.role &&
                phone == user.phone &&
                status == user.status &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(date_registration, user.date_registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, login, password, email, phone, name, surname, status, date_registration);
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", status=" + status +
                ", date_registration=" + date_registration +
                '}';
    }
}

