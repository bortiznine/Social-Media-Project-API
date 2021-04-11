package com.socialmedia.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaRepository extends JpaRepository<Post, Long> {
}
