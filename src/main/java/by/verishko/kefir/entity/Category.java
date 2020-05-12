package by.verishko.kefir.entity;

import java.util.Objects;

public class Category extends Entity {
    private Integer idCatalog;
    private String name;

    public Integer getIdCatalog() {
        return idCatalog;
    }

    public void setIdCatalog(Integer idCatalog) {
        this.idCatalog = idCatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return idCatalog.equals(category.idCatalog) &&
                name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idCatalog, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCatalog=" + idCatalog +
                ", name='" + name + '\'' +
                '}';
    }
}
