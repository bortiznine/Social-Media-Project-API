package com.socialmedia.demo.repository;

import com.socialmedia.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByName(String categoryName);
    List<Post> findByUserId(Long userId);

    //find by user and category then return the category
    Post findByIdAndUserId(Long categoryId, Long userId);

    //find by user id and category name
    Post findByUserIdAndName(Long userId, String categoryName);

}
