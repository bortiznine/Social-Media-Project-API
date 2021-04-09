package com.socialmedia.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class SocialMediaController {


    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello world";
    }

    // return all of a users posts
    @GetMapping("/posts")
    public List<Post> getPosts() {
        System.out.println("getting getPosts");
        return socialMediaService.getPosts();
    }

    // get a single post
    @GetMapping("/posts/{postId}")
    public Post getPost() {
        System.out.println("calling getPost");
        return socialMediaService.getPosts();
    }

    // create a new post
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post postObject) {
        System.out.println("calling createPost");
        return socialMediaService.createPost(postObject);
    }

    // update a post
    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post postObject) {
        System.out.println("calling updateCategory");
        return socialMediaService.updatePost(postId, postObject);
    }

    // delete a post

}
