package by.verishko.kefir.entity;

import java.util.Objects;

public class Comment extends Entity {
    private Integer idComment;
    private String name;
    private String commentText;
    private Integer user_id;
    private Integer product_id;

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(final Integer user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(final Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return user_id.equals(comment.user_id) &&
                product_id.equals(comment.product_id) &&
                idComment.equals(comment.idComment) &&
                name.equals(comment.name) &&
                commentText.equals(comment.commentText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idComment, name, commentText, user_id, product_id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", name='" + name + '\'' +
                ", commentText='" + commentText + '\'' +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                '}';
    }
}
