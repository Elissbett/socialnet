package com.ebolotina.socialnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User author;

    private String text;

    private Date createdDate;
    //TODO:как работает hibernate. Стуктура таблиц.
    //TODO:как хратить ленту
    //TODO:записи от имени сообщества

    public Post() {}

    public Post(PostBuilder builder) {
        this.author = builder.author;
        this.text = builder.text;
        this.createdDate = builder.createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public static PostBuilder  post() {
        return new PostBuilder();
    }

    public static class PostBuilder {

        private String text;
        private User author;
        private Date createdDate;

        public PostBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public PostBuilder withAuthor(User author) {
            this.author = author;
            return this;
        }

        public PostBuilder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
