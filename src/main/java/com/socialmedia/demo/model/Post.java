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
    private String title;

@Column
    private String content;

@Column
    private Date date;

@ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

@OneToMany(mappedBy = "post", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> commentList;




}
