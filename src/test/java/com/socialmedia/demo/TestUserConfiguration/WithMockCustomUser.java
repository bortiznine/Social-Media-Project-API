package com.socialmedia.demo.TestUserConfiguration;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    String id() default "1";
    String username() default "testuser";
    String emailAddress() default "test@example.org";
    String password() default "password";
    String picture() default "";
}