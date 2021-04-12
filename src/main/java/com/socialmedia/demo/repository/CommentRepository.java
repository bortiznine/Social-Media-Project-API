package com.socialmedia.demo.repository;

import com.socialmedia.demo.model.Comment;

import com.socialmedia.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long userId);

    Optional<Comment> findByPostId(Long postId);

    List<Comment> findByPostIdAndUserId(Long postId, Long userId);
}
