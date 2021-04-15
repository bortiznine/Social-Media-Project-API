package com.socialmedia.demo;

import com.socialmedia.demo.controller.SocialMediaController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private SocialMediaController socialMediaController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(socialMediaController).isNotNull();
    }

}
