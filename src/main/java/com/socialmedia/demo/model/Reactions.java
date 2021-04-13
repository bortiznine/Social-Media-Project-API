package com.socialmedia.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long like;

    @Column
    private Long laugh;

    @Column
    private Long angry;

    @Column
    private Long sad;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonIgnore
    private Post post;


    public Reactions() {
    }

    public Reactions(Long id, Long like, Long laugh, Long angry, Long sad) {
        this.id = id;
        this.like = like;
        this.laugh = laugh;
        this.angry = angry;
        this.sad = sad;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getLaugh() {
        return laugh;
    }

    public void setLaugh(Long laugh) {
        this.laugh = laugh;
    }

    public Long getAngry() {
        return angry;
    }

    public void setAngry(Long angry) {
        this.angry = angry;
    }

    public Long getSad() {
        return sad;
    }

    public void setSad(Long sad) {
       this.sad = sad;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "Reactions{" +
                "Id=" + id +
                ", Like=" + like +
                ", Laugh=" + laugh +
                ", Angry=" + angry +
                ", Sad=" + sad +
//                ", user=" + user +
                '}';
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
