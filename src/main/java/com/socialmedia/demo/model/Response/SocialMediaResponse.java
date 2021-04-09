package com.socialmedia.demo.model.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socialmedia.demo.model.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class SocialMediaResponse {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy= "category", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)

    //Add Post response class in response package
    private List<Post> postList;
    //many categories can belong to one user
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;


    public SocialMediaResponse(Long id, String name, String about) {
        this.id = id;
        this.name = name;
        this.about = about;
    }

    public SocialMediaResponse() {

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

    public String getAbout() {
        return description;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Social Media Response{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                '}';
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setRecipeList(List<Post> postList) {
        this.postList = postList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
