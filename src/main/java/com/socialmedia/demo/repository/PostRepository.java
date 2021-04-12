package com.socialmedia.demo.repository;

import com.socialmedia.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String postTitle);
    List<Post> findByUserId(Long userId);

    //find by user and category then return the category
    Post findByIdAndUserId(Long postId, Long userId);

    //find by user id and category name
    Post findByUserIdAndTitle(Long userId, String postTitle);

}
