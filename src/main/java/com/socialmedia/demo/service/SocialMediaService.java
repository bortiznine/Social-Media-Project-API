package com.socialmedia.demo.service;

import com.socialmedia.demo.exception.InformationExistException;
import com.socialmedia.demo.exception.InformationNotFoundException;
import com.socialmedia.demo.model.SocialMedia;
import com.socialmedia.demo.repository.CategoryRepository;
import com.socialmedia.demo.repository.RecipeRepository;
import com.socialmedia.demo.security.MyUserDetails;
import com.socialmedia.demo.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SocialMediaService {
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Category> getCategories() {
        System.out.println("service calling getCategories ==>");
        MyUserDetails userDetails=(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser());
        List<Category> categories = categoryRepository.findByUserId(userDetails.getUser().getId());
        if(categories.isEmpty()){
            throw new InformationNotFoundException("no categories found for that userID " + userDetails.getUser().getId());
        }
        else{
            return categories;
        }

    }

    public Category getCategory(Long categoryId) {
        System.out.println("service getCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            return category;
        }
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("service calling createCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByUserIdAndName(userDetails.getUser().getId(), categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            categoryObject.setUser(userDetails.getUser());
            return categoryRepository.save(categoryObject);
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByIdAndUserId(userDetails.getUser().getId(), categoryId);
        if (category!=null) {
            if (categoryObject.getName().equals(category.getName())) {
                throw new InformationExistException("category with name " + category.getName() + " already exist");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InformationNotFoundException("category id " + categoryId + " not found");
        }
    }

    public Category deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryRepository.findByIdAndUserId(userDetails.getUser().getId(), categoryId);
        if (category!=null) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with ID " + categoryId + " not found!");
        }

    }


    public Recipe createCategoryRecipe(Long categoryId, Recipe recipeObject){
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


