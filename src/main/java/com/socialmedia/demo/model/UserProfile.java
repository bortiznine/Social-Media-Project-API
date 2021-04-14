//package com.socialmedia.demo.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="profiles")
//public class UserProfile {
//
//    @Id
//    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String firstName;
//
//    @Column
//    private String lastName;
//
//    @Column
//    private String about;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "userProfile")
//    private User user;
//
//    public UserProfile() {
//    }
//
//    public UserProfile(Long id, String firstName, String lastName, String about) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.about= about;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAbout() {
//        return about;
//    }
//
//    public void setAbout(String about) {
//        this.about = about;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "UserProfile{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", about='" + about + '\'' +
//                '}';
//    }
//}
