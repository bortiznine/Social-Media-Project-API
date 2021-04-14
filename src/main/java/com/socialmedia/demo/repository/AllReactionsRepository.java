package com.socialmedia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmedia.demo.model.AllReactions;

public interface AllReactionsRepository extends JpaRepository<AllReactions, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
