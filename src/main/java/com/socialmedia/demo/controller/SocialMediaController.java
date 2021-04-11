package com.socialmedia.demo.controller;

import com.socialmedia.demo.model.Post;
import com.socialmedia.demo.model.Comment;
import com.socialmedia.demo.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class SocialMediaController {

    private SocialMediaService socialMediaService;

    @Autowired
    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello world";
    }

     //return all of a users posts
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        System.out.println("getting getPosts");
        return socialMediaService.getAllPosts();
    }

    // get a single post
    @GetMapping("/posts/{postId}")
    public Post getSinglePost(@PathVariable Long postId) {
        System.out.println("calling getPost");
        return socialMediaService.getSinglePost(postId);
    }

    // create a new post
    @PostMapping("/posts")
    public Post createSinglePost(@RequestBody Post postObject) {
        System.out.println("calling createPost");
        return socialMediaService.createSinglePost(postObject);
    }

    // update a post
    @PutMapping("/posts/{postId}")
    public Post updateSinglePost(@PathVariable Long postId, @RequestBody Post postObject) {
        System.out.println("calling updateSinglePost");
        return socialMediaService.updateSinglePost(postId, postObject);
    }

    // delete a post
    @DeleteMapping("/posts/{postId}")
    public Post deleteSinglePost(@PathVariable Long postId) {
        System.out.println("calling deletePost");
        return socialMediaService.deleteSinglePost(postId);
    }

    // delete all post
    @DeleteMapping("/posts")
    public List<Post> deleteAllPosts() {
        System.out.println("calling deleteAllPosts");
        return socialMediaService.deleteAllPosts();
    }


    // make a comment on a post
    @PostMapping("/posts/{postId}/comments")
    public Comment commentOnPost(@PathVariable Long postId, @PathVariable Post postObject) {
        System.out.println("calling commentOnPost");
        return socialMediaService.commentOnPost(postId, postObject);
    }

    // get all comments on a post


    // edit a comment on a post


    // delete a comment on a post

}
