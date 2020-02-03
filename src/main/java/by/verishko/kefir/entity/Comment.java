package by.verishko.kefir.entity;

import java.util.Objects;

public class Comment extends Entity {
    private String name;
    private String body;
    private int users_id;
    private int goods_id;

    public Comment() {
    }

    public Comment(String name, String body, int users_id, int goods_id) {
        this.name = name;
        this.body = body;
        this.users_id = users_id;
        this.goods_id = goods_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
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
        Comment comment = (Comment) o;
        return users_id == comment.users_id &&
                goods_id == comment.goods_id &&
                Objects.equals(name, comment.name) &&
                Objects.equals(body, comment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, body, users_id, goods_id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", users_id=" + users_id +
                ", goods_id=" + goods_id +
                '}';
    }
}
