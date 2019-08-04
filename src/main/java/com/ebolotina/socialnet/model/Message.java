package com.ebolotina.socialnet.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne
    private User owner;

    @ManyToOne
    private User party;

    private Boolean outgoing;

    private Date createdDate;

    public Message() { /* Default constructor for Hibernate */ }

    public Message(MessageBuilder messageBuilder) {
        this.text = messageBuilder.text;
        this.owner = messageBuilder.owner;
        this.party = messageBuilder.party;
        this.outgoing = messageBuilder.outgoing;
        this.createdDate = messageBuilder.createdDate;
    }

    public Boolean isOutgoing() {
        return outgoing;
    }

    public void setOutgoing(Boolean outgoing) {
        this.outgoing = outgoing;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getParty() {
        return party;
    }

    public void setParty(User party) {
        this.party = party;
    }

    public static MessageBuilder message() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {

        private String text;
        private User owner;
        private User party;
        private Boolean outgoing;
        private Date createdDate;

        public MessageBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public MessageBuilder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public MessageBuilder withParty(User party) {
            this.party = party;
            return this;
        }

        public MessageBuilder withCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public MessageBuilder withOutgoing(Boolean outgoing) {
            this.outgoing = outgoing;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
