package com.socialmedia.demo.repository;

import com.socialmedia.demo.model.ReactionsCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionsCountRepository extends JpaRepository<ReactionsCount, Long> {
    ReactionsCount findByIdAndPostId(Long Id, Long postId);
    ReactionsCount findByPostId(Long postId);
}
