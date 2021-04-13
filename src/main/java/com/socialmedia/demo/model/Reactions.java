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
    private Long likes = 0L;

    @Column
    private Long laugh = 0L;

    @Column
    private Long angry = 0L;

    @Column
    private Long sad = 0L;

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

    public Reactions(Long id, Long likes, Long laugh, Long angry, Long sad) {
        this.id = id;
        this.likes = likes;
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
        return likes;
    }

    public void setLike(Long like) {
        this.likes = like;
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
                ", Like=" + likes +
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
