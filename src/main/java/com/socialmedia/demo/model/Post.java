package com.socialmedia.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;


    @Column
    private String title;

    @Column(length = 1000)
    private String content;

    @Column
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reactions_count_id", referencedColumnName="id")
    private ReactionsCount reactionsCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reactions_id", referencedColumnName="id")
    @JsonIgnore
    private Reactions reactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

@OneToMany(mappedBy = "post", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> comments;



    public Post() {
    }


    public Post(Long id, String title, String content, Date date, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ReactionsCount getReactionsCount() {
        return reactionsCount;
    }

    public void setReactionsCount(ReactionsCount reactionsCount) {
        this.reactionsCount = reactionsCount;
    }

    public Reactions getReactions() {
        return reactions;
    }

    public void setReactions(Reactions reactions) {
        this.reactions = reactions;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username= "+username+
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
//reference back to here if need be delete a getter and or setter for project to work.
