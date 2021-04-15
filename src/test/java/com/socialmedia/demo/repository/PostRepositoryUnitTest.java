package com.socialmedia.demo.repository;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.socialmedia.demo.model.Post;
import com.socialmedia.demo.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PostRepositoryUnitTest {

    private User userA;
    private User userB;
    private Post postA1;
    private Post postA2;
    private Post postB1;
    private Post postB2;



    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;



    @BeforeEach
    public void setUp() {
        this.userA = new User("testUserA", "exampleA@google.com", "password", null);
        userRepository.save(userA);
        this.postA1 = new Post("Test title 1 by UserA", "Test content body of the post", new Date(), userA.getUsername());
        this.postA2 = new Post("Test title 2 by UserA", "Test content body of the post", new Date(), userA.getUsername());
        postA1.setUser(userA);
        postA2.setUser(userA);

        this.userB = new User("testUserB", "exampleB@google.com", "password", null);
        userRepository.save(userB);
        this.postB1 = new Post("Test title 1 by UserB", "Other test content body of the post", new Date(), "testUser");
        this.postB2 = new Post("Test title 2 by UserB", "Other test content body of the post", new Date(), "testUser");
        postB1.setUser(userB);
        postB2.setUser(userB);

        postRepository.saveAll(List.of(postA1, postA2, postB1, postB2));
    }

    @AfterEach
    public void tearDown() {
        postRepository.deleteAll(List.of(postA1, postA2, postB1, postB2));
        userRepository.deleteAll(List.of(userA, userB));
    }

    @Test
    public void shouldFindPostByPostId() {
        Optional<Post> foundPost = postRepository.findById(postA1.getId());
        assertEquals(foundPost.get().getId(), postA1.getId());

    }

    @Test
    public void shouldFindPostByUserIdAndPostTitle() {
        Post foundPost = postRepository.findByUserIdAndTitle(userA.getId(), postA1.getTitle());
        assertEquals(foundPost, postA1);

        foundPost = postRepository.findByUserIdAndTitle(userB.getId(), postB1.getTitle());
        assertEquals(foundPost, postB1);
    }

    @Test
    public void shouldFindAllPostsByUserId() {
        List<Post> allUserAPosts = postRepository.findByUserId(userA.getId());
        assertEquals(allUserAPosts, List.of(postA1, postA2));
    }

}
