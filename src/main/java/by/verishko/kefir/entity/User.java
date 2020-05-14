package by.verishko.kefir.entity;

import by.verishko.kefir.entity.enumEntity.Role;

import java.time.LocalDate;
import java.util.Objects;

public class User extends Entity {
    private Integer idUser;
    private Role role;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String surname;
    private Boolean activeStatus;
    private LocalDate date_registration;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public Boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(final Integer id) {
        if (id == 0) {
            this.activeStatus = false;
        } else if (id == 1) {
            this.activeStatus = true;
        }
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
        return idUser.equals(user.idUser) &&
                role == user.role &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                phone.equals(user.phone) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                activeStatus.equals(user.activeStatus) &&
                date_registration.equals(user.date_registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idUser, role, login, password, email, phone, name, surname, activeStatus, date_registration);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", activeStatus=" + activeStatus +
                ", date_registration=" + date_registration +
                '}';
    }
}