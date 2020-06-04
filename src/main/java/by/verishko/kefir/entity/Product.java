package by.verishko.kefir.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Product extends Entity {
    private Integer idProduct;
    private String name;
    private String description;
    private BigDecimal price;
    private Timestamp date_creation;
    private int user_id;
    private int category_id;
    private int location_id;

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
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

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(final Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return user_id == product.user_id &&
                category_id == product.category_id &&
                location_id == product.location_id &&
                idProduct.equals(product.idProduct) &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                price.equals(product.price) &&
                date_creation.equals(product.date_creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idProduct, name, description, price, date_creation, user_id, category_id, location_id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date_creation=" + date_creation +
                ", user_id=" + user_id +
                ", category_id=" + category_id +
                ", location_id=" + location_id +
                '}';
    }
}
