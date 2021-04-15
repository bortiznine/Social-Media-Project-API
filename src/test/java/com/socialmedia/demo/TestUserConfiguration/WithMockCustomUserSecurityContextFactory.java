package com.socialmedia.demo.TestUserConfiguration;

import com.socialmedia.demo.model.User;
import com.socialmedia.demo.security.MyUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User testUser = new User(Long.parseLong(customUser.id()),
                                customUser.username(),
                                customUser.emailAddress(),
                                customUser.password(),
                                customUser.picture());

        MyUserDetails myUserDetails = new MyUserDetails(testUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
