package com.example.api.categories;


import com.example.api.posts.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "api/categories", consumes="application/json")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // api getall
    @GetMapping
    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.getCategories();
    }


    // api get list of post under category
    @GetMapping(path = "{categoryId}/posts")
    @ResponseBody
    public Set<Post> getCategoryPosts(@PathVariable("categoryId") Long categoryId){
        return (Set<Post>) categoryService.getPostsUnderCategory(categoryId);
    }


    // api get a category
    @GetMapping(path = "{categoryId}")
    public Category getACategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.getDetailedCategory(categoryId);

    }


    //api post
    @PostMapping
    public Category addACategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    // api delete
    @DeleteMapping(path = "{categoryId}")
    public Map<String, String> deleteACategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    // put
    @PutMapping(path = "{categoryId}")
    public Category updateACategory(@PathVariable("categoryId") Long categoryId,
                                    @RequestBody Category categorys) {
       return categoryService.updateCategory(categoryId, categorys);
    }


}
