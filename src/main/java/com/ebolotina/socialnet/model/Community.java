package com.ebolotina.socialnet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Community {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> members;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> admins;

    private Date createdDate;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Post> feed;

    Community() {}

    Community(CommunityBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.members = builder.members;
        this.admins = builder.admins;
        this.feed = builder.feed;
        this.createdDate = builder.createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<Post> getFeed() {
        return feed;
    }

    public void setFeed(Set<Post> feed) {
        this.feed = feed;
    }

    public static CommunityBuilder community() {
        return new CommunityBuilder();
    }

    public static class CommunityBuilder {

        private String name;
        private String description;
        private Set<User> members;
        private Set<User> admins;
        private Date createdDate;
        private Set<Post> feed;

        public CommunityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CommunityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CommunityBuilder withMembers(Set<User> members) {
            this.members = members;
            return this;
        }

        public CommunityBuilder withAdmins(Set<User> admins) {
            this.admins = admins;
            return this;
        }

        public CommunityBuilder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public CommunityBuilder withFeed(Set<Post> feed) {
            this.feed = feed;
            return this;
        }

        public Community build() {
            return new Community(this);
        }
    }

}
