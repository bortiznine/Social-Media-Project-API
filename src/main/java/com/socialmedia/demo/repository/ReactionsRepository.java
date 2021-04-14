package com.socialmedia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmedia.demo.model.Reactions;

public interface ReactionsRepository extends JpaRepository<Reactions, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
