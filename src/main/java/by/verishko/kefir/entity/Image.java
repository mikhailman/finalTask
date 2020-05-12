package by.verishko.kefir.entity;

import java.util.Objects;

public class Image extends Entity {
    private Integer idImage;
    private String name;
    private String image_path;
    private int product_id;

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Image image = (Image) o;
        return product_id == image.product_id &&
                idImage.equals(image.idImage) &&
                name.equals(image.name) &&
                image_path.equals(image.image_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idImage, name, image_path, product_id);
    }

    @Override
    public String toString() {
        return "Image{" +
                "idImage=" + idImage +
                ", name='" + name + '\'' +
                ", image_path='" + image_path + '\'' +
                ", product_id=" + product_id +
                '}';
    }
}
