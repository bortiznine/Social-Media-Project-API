package com.socialmedia.demo.repository;

import com.socialmedia.demo.model.Reactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionsRepository extends JpaRepository<Reactions, Long> {
    Reactions findByIdAndPostId(Long reactionId, Long postId);
}
