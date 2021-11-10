package com.example.api.categories;

import com.example.api.posts.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

// creating the category class
@Data
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    private Long categoryId;
    private String name;
    private String description;

    // defining the relationship between the author and post.
    @JsonIgnore
    @OneToMany(mappedBy = "category_fk", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> postcategory;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "category_fk", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Post> postcategory = new HashSet<>();


    // creating the constructor for the category class
    public Category(){}

    public Category(Long categoryId, String name, String description, Set<Post> postcategory) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.postcategory = postcategory;
    }

    public Category(String name, String description, Set<Post> postcategory) {
        this.name = name;
        this.description = description;
        this.postcategory = postcategory;
    }

    // here I created the getters and the setters for the category class
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Post> getPostcategory() {
        return postcategory;
    }

    public void setPostcategory(Set<Post> postcategory) {
        this.postcategory = postcategory;
    }

    // converting the category into a string
    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", postcategory='" + postcategory + '\'' +
                '}';
    }
}
