package by.verishko.kefir.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product extends Entity {
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate date_creation;
    private int users_id;
    private int category_id;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, LocalDate date_creation, int users_id, int category_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.date_creation = date_creation;
        this.users_id = users_id;
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return users_id == product.users_id &&
                category_id == product.category_id &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(date_creation, product.date_creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, price, date_creation, users_id, category_id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date_creation=" + date_creation +
                ", users_id=" + users_id +
                ", category_id=" + category_id +
                '}';
    }
}
