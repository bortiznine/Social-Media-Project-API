package com.socialmedia.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private Long Like;

    @Column
    private Long Laugh;

    @Column
    private Long Angry;

    @Column
    private Long Sad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Reactions() {
    }

    public Reactions(Long id, Long like, Long laugh, Long angry, Long sad) {
        Id = id;
        Like = like;
        Laugh = laugh;
        Angry = angry;
        Sad = sad;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getLike() {
        return Like;
    }

    public void setLike(Long like) {
        Like = like;
    }

    public Long getLaugh() {
        return Laugh;
    }

    public void setLaugh(Long laugh) {
        Laugh = laugh;
    }

    public Long getAngry() {
        return Angry;
    }

    public void setAngry(Long angry) {
        Angry = angry;
    }

    public Long getSad() {
        return Sad;
    }

    public void setSad(Long sad) {
        Sad = sad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reactions{" +
                "Id=" + Id +
                ", Like=" + Like +
                ", Laugh=" + Laugh +
                ", Angry=" + Angry +
                ", Sad=" + Sad +
                ", user=" + user +
                '}';
    }
}
