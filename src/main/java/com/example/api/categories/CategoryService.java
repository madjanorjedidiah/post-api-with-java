package com.example.api.categories;


import com.example.api.posts.Post;
import com.example.api.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    PostRepository postRepository;


    // get all categories
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    //  add categories
    public Category addCategory(Category category){

        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent()){
            throw new IllegalStateException("Name already exists.");
        };
        return categoryRepository.save(category);
    }

    // delete posts
    public Map<String, String> deleteCategory(Long categoryId){
        // check if ID exists
        boolean exists = categoryRepository.existsById(categoryId);
        if (!exists){
            throw new IllegalStateException("Category with ID " + categoryId + " does not exist.");
        }
        //delete by id
        categoryRepository.deleteById(categoryId);
        HashMap<String, String> map = new HashMap<>();
        map.put("Deleted category Id", " " + categoryId + " ");
        return map;
    }

    // update category
    @Transactional
    public Category updateCategory(Long categoryId, Category categorys) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new IllegalStateException("Category with ID " + categoryId + " does not exist."));

        // updating fields in the db
        Optional<Category> categoryOptional = categoryRepository.findByName(categorys.getName());
        if (categoryOptional.isPresent()) {
            throw new IllegalStateException("Name already exists.");
        }
        category.setName(categorys.getName());
        category.setDescription(categorys.getDescription());

        return categoryRepository.save(category);
    }


    // get particular  category
    public Category getDetailedCategory(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new IllegalStateException("Category with ID " + categoryId + " does not exist."));
        return category;
    }

    // get posts under categpry
    public Set<Post> getPostsUnderCategory(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new IllegalStateException("Category with ID " + categoryId + " does not exist."));
        return category.getPostcategory();

    }
}
