package com.socialmedia.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "all_reactions")
public class AllReactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @Column
    private String type;

    public AllReactions(Long id, User user, Post post, String type) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.type = type;
    }

    public AllReactions() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


