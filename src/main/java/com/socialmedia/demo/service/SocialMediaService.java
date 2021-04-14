package com.socialmedia.demo.service;

import com.socialmedia.demo.exception.InformationExistException;
import com.socialmedia.demo.exception.InformationNotFoundException;
import com.socialmedia.demo.exception.ReactionInvalidException;
import com.socialmedia.demo.model.Reactions;
import com.socialmedia.demo.model.Post;
import com.socialmedia.demo.model.Comment;
import com.socialmedia.demo.model.ReactionsCount;
import com.socialmedia.demo.repository.CommentRepository;
import com.socialmedia.demo.repository.PostRepository;
import com.socialmedia.demo.repository.ReactionsCountRepository;
import com.socialmedia.demo.repository.ReactionsRepository;
import com.socialmedia.demo.security.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocialMediaService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReactionsCountRepository reactionsCountRepository;

    @Autowired
    private ReactionsRepository reactionsRepository;

    @Autowired
    public void setSocialMediaRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



    public List<Post> getAllPosts() {
        System.out.println("service calling getAllPosts ==>");
        return postRepository.findAll();
    }

    public Post getSinglePost(Long postId) {
        System.out.println("service getSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post == null) {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        } else {
            return  post;
        }
    }

    public Post createSinglePost(Post postObject) {
        System.out.println("service calling createSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByUserIdAndTitle(userDetails.getUser().getId(), postObject.getTitle());
        if (post != null) {
            throw new InformationExistException("post with title " + post.getTitle() + " already exists");
        } else {
            ReactionsCount reactionsCount = new ReactionsCount();
            reactionsCount.setPost(postObject);
            postObject.setReactionsCount(reactionsCount);
            postObject.setUser(userDetails.getUser());
            postObject.setDate(new Date());
            return postRepository.save(postObject);
        }
    }

    public Post updateSinglePost(Long postId, Post postObject) {
        System.out.println("service calling updateSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post != null) {
            if (post.getTitle().equals(postObject.getTitle())) {
                throw new InformationExistException("post with title " + post.getTitle() + " already exist");
            } else {
                post.setTitle(postObject.getTitle());
                post.setContent(postObject.getContent());
                post.setDate(new Date()); // edit time & date
                return postRepository.save(post);
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
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            commentObject.setPost(post.get());
            commentObject.setDate(new Date());
            commentObject.setUser(userDetails.getUser());
            return commentRepository.save(commentObject);
        } else {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }


    public List<Comment> getAllCommentsOnPost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            return optionalPost.get().getComments();
        }
        else {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }

    public Comment editCommentOnPost(Long commentId, Comment commentObject) {
        System.out.println("service calling updatePostComment==>");
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setText(commentObject.getText());
            comment.setDate(new Date()); // edit time & date
            return commentRepository.save(comment);
        } else {
            throw new InformationNotFoundException("comment or post not found");
        }
    }

    public ResponseEntity<?> deleteCommentOnPost(Long postId, Long commentId) {
        System.out.println("service calling deleteCommentsOnPost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post == null) {
            throw new InformationNotFoundException("post with id " + postId +
                    " does not belongs to this user or post does not exist");
        }
        Optional<Comment> comment = commentRepository.findByIdAndPostId(commentId, postId);
        if (comment.isEmpty()) {
            throw new InformationNotFoundException("comment with id " + commentId +
                    " does not belongs to this user or comment does not exist");
        } else {
            commentRepository.deleteById(comment.get().getId());
        }
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "comment with id: " + commentId + " was successfully deleted");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAllCommentsOnPost(Long postId) {
        System.out.println("service calling deleteAllCommentsOnPost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Post post = postRepository.findByIdAndUserId(postId, userDetails.getUser().getId());
        if (post == null) {
            throw new InformationNotFoundException("post with id " + postId +
                    " does not belongs to this user or post does not exist");
        } else {
            List<Comment> comments = commentRepository.findByPostIdAndUserId(postId, userDetails.getUser().getId());
            commentRepository.deleteAll(comments);
        }
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "all comments on post with id: " + postId + " were successfully deleted");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public Post postReactions(String reactionType, Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (post.isPresent()) {
            ReactionsCount reactionsCount = reactionsCountRepository.findByPostId(postId);
            boolean alreadyReacted = reactionsRepository.existsByUserIdAndPostId(userDetails.getUser().getId(), postId);
            if (alreadyReacted) {
                throw new InformationExistException("reaction of "+ reactionType + " cannot be added as there is another reaction submitted!");
            }
            Reactions newReactions = new Reactions();
            switch (reactionType) {
                case "like":

                    Long likesCount = reactionsCount.getLike();
                    likesCount++;
                    reactionsCount.setLike(likesCount);

                    break;
                case "laugh":
                    Long laughCount = reactionsCount.getLaugh();
                    laughCount++;
                    reactionsCount.setLaugh(laughCount);
                    break;
                case "angry":
                    Long angryCount = reactionsCount.getAngry();
                    angryCount++;
                    reactionsCount.setAngry(angryCount);
                    break;
                case "sad":
                    Long sadCount = reactionsCount.getSad();
                    sadCount++;
                    reactionsCount.setSad(sadCount);
                    break;
                default:
                    throw new ReactionInvalidException("User trying to submit a reaction cannot find " + reactionType + " reaction");
            }
            newReactions.setUser(userDetails.getUser());
            newReactions.setPost(post.get());
            reactionsRepository.save(newReactions);
            reactionsCountRepository.save(reactionsCount);
            return post.get();
        } else {
            throw new InformationNotFoundException("Post with id " + postId + " not found");
        }
    }
}


