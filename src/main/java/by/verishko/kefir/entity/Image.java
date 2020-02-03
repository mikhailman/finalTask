package by.verishko.kefir.entity;

import java.util.Objects;

public class Image extends Entity {
    private String name;
    private String image_path;
    private int goods_id;

    public Image() {
    }

    public Image(String name, String image_path, int goods_id) {
        this.name = name;
        this.image_path = image_path;
        this.goods_id = goods_id;
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

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Image image = (Image) o;
        return goods_id == image.goods_id &&
                Objects.equals(name, image.name) &&
                Objects.equals(image_path, image.image_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, image_path, goods_id);
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", image_path='" + image_path + '\'' +
                ", goods_id=" + goods_id +
                '}';
    }
}
