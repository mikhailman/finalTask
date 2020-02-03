package by.verishko.kefir.entity;

import java.util.Objects;

public class Category extends Entity {
    private String name;
    private int parent_id;

    public Category() {
    }

    public Category(String name, int parent_id) {
        this.name = name;
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Category category = (Category) o;
//        return id == category.id &&
//                parent_id == category.parent_id &&
//                Objects.equals(name, category.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, parent_id);
//    }
//
//    @Override
//    public String toString() {
//        return "\nCategory{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", parent_id=" + parent_id +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return parent_id == category.parent_id &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, parent_id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }
}
