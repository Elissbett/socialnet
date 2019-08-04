package com.ebolotina.socialnet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String secondName;

    private Date dateOfBirth;

    private Date createdDate;//TODO:create base model

    //TODO:Lazy doesn't work
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> friends;

    public User() {}

    public User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.dateOfBirth = builder.dateOfBirth;
        this.createdDate = builder.createdDate;
        this.friends = builder.friends;
    }

    public static UserBuilder user() {
        return new UserBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public static class UserBuilder {

        private String firstName;
        private String secondName;
        private Date dateOfBirth;
        private Date createdDate;
        private Set<User> friends;

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public UserBuilder withDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public UserBuilder withFriends(Set<User> friends) {
            this.friends = friends;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
