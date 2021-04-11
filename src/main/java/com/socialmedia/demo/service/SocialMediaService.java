package com.socialmedia.demo.service;

import com.socialmedia.demo.exception.InformationExistException;
import com.socialmedia.demo.exception.InformationNotFoundException;
import com.socialmedia.demo.model.Post;
import com.socialmedia.demo.repository.PostRepository;
import com.socialmedia.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SocialMediaService {

    private PostRepository postRepository;

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
        Post post = postRepository.findByUserIdAndName(userDetails.getUser().getId(), postObject.getTitle());
        if (post != null) {
            throw new InformationExistException("post with name " + post.getTitle() + " already exists");
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
                throw new InformationExistException("post with name " + post.getTitle() + " already exist");
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

    public Post deleteSinglePost(Long postId) {
        System.out.println("service calling deleteSinglePost ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findByIdAndUserId(userDetails.getUser().getId(), postId);
        if (post != null) {
            postRepository.deleteById(postId);
            return post;
        } else {
            throw new InformationNotFoundException("post with ID " + postId + " not found!");
        }
    }

    public List<Post> deleteAllPosts() {
        System.out.println("service calling deleteAllPosts ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> posts = postRepository.findByUserId(userDetails.getUser().getId());
        if (!posts.isEmpty()) {
            postRepository.deleteAll(posts);
            return posts;
        } else {
            throw new InformationNotFoundException("Could not find any posts for user " + userDetails.getUsername());
        }
    }



    public Comment createCategoryRecipe(Long categoryId, Recipe recipeObject){
        System.out.println("service calling createCategoryRecipe =====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
            //Setting and casting the datatype to the category for the recipe
            recipeObject.setCategory(category);
            return recipeRepository.save(recipeObject);
        }catch(NoSuchElementException e){
            throw new InformationNotFoundException("category with id " + categoryId +  " not found");
        }
    }

    public List<Recipe> getCategoryRecipe(Long categoryId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
        if (category!=null) {
            return category.getRecipeList();
        }
        else {
            throw new InformationNotFoundException("category id " + categoryId + " not found");
        }
    }
    public Recipe updateCategoryRecipe(Long categoryId, Long recipeId, Recipe recipeObject) {
        System.out.println("service calling updateCategoryRecipe==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category=this.getCategory(categoryId);
        recipeObject.setCategory(category);
        recipeObject.setUser(userDetails.getUser());
        try {
            return recipeRepository.save(recipeObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("recipe or category not found");
        }
    }

    public void deleteCategoryRecipe(Long categoryId, Long recipeId) {
        System.out.println("service calling deleteCategoryRecipe ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        recipeRepository.deleteById(recipe.get().getId());
    }

}


