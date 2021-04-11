package com.socialmedia.demo.repository;

import com.socialmedia.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //to register
    boolean existsByEmailAddress(String userEmailAddress);
    User findByEmailAddress(String userEmailAddress);
}
