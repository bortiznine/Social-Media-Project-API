package com.socialmedia.demo.service;

import com.socialmedia.demo.exception.InformationExistException;
import com.socialmedia.demo.exception.InformationNotFoundException;
import com.socialmedia.demo.model.Post;
import com.socialmedia.demo.model.Comment;
import com.socialmedia.demo.repository.CommentRepository;
import com.socialmedia.demo.repository.PostRepository;
import com.socialmedia.demo.security.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SocialMediaService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public void setSocialMediaRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        System.out.println("service calling getAllPosts ==>");
        MyUserDetails userDetails=(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser());
        List<Post> posts = postRepository.findByUserId(userDetails.getUser().getId());
        if (posts.isEmpty()){
            throw new InformationNotFoundException("no posts found for that userID " + userDetails.getUser().getId());
        }
        else{
            return posts;
        }

    }

    public Post getSinglePost(Long postId) {
        System.out.println("service getSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post == null) {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        } else {
            return post;
        }
    }

    public Post createSinglePost(Post postObject) {
        System.out.println("service calling createSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByUserIdAndTitle(userDetails.getUser().getId(), postObject.getTitle());
        if (post != null) {
            throw new InformationExistException("post with title " + post.getTitle() + " already exists");
        } else {
            postObject.setUser(userDetails.getUser());
            return postRepository.save(postObject);
        }
    }

    public Post updateSinglePost(Long postId, Post postObject) {
        System.out.println("service calling updateSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByIdAndUserId(userDetails.getUser().getId(), postId);
        if (post != null) {
            if (post.getTitle().equals(postObject.getTitle())) {
                throw new InformationExistException("post with title " + post.getTitle() + " already exist");
            } else {
                Post updatePost = postRepository.findById(postId).get();
                updatePost.setTitle(post.getTitle());
                updatePost.setContent(post.getContent());
                return postRepository.save(updatePost);
            }
        } else {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }

    public ResponseEntity<?> deleteSinglePost(Long postId) {
        System.out.println("service calling deleteSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByIdAndUserId(userDetails.getUser().getId(), postId);
        if (post != null) {
            postRepository.deleteById(postId);
            HashMap<String, String> responseMessage = new HashMap<>();
            responseMessage.put("status", "post with id: " + postId + " was successfully deleted");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }

    public ResponseEntity<?> deleteAllPosts() {
        System.out.println("service calling deleteAllPosts ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> posts = postRepository.findByUserId(userDetails.getUser().getId());
        if (!posts.isEmpty()) {
            postRepository.deleteAll(posts);
            HashMap<String, String> responseMessage = new HashMap<>();
            responseMessage.put("status", "all posts for user " + userDetails.getUsername() + " successfully deleted");
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            throw new InformationNotFoundException("Could not find any posts for user " + userDetails.getUsername());
        }
    }

    public Comment commentOnPost(Long postId, Comment commentObject) {
        System.out.println("service calling commentOnPost =====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
            //Setting and casting the datatype to the post for the comment
            commentObject.setPost(post);
            return commentRepository.save(commentObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }


    public List<Comment> getAllCommentsOnPost(Long postId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post!=null) {
            return post.getCommentList();
        }
        else {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }

    public Comment editCommentOnPost(Long postId, Long commentId, Comment commentObject) {
        System.out.println("service calling updatePostComment==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = this.getSinglePost(postId);
        commentObject.setPost(post);
        commentObject.setUser(userDetails.getUser());
        try {
            return commentRepository.save(commentObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("comment or post not found");
        }
    }

    public ResponseEntity<?> deleteCommentOnPost(Long postId, Long commentId) {
        System.out.println("service calling deletePostComment ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post == null) {
            throw new InformationNotFoundException("post with id " + postId +
                    " does not belongs to this user or post does not exist");
        }
        Optional<Comment> comment = commentRepository.findByPostId(
                postId).stream().filter(c -> c.getId().equals(commentId)).findFirst();
        if (comment.isEmpty()) {
            throw new InformationNotFoundException("comment with id " + commentId +
                    " does not belongs to this user or comment does not exist");
        }
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "comment with id: " + commentId + " was successfully deleted");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}


