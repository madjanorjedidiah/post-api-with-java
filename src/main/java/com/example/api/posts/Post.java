package com.example.api.posts;

import com.example.api.authors.Author;
import com.example.api.categories.Category;

import javax.persistence.*;

// creating the post class which is a representation of the table to be created in the database
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"title"}))
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    @Column(name = "post_id")
    private Long postId;
    private String title;
    private String text;


//    @ManyToMany
//    @JoinTable(name = "post_category",
//                joinColumns = @JoinColumn(name = "postId"),
//                inverseJoinColumns =  @JoinColumn(name = "categoryId"))
//    private Set<Category> category_fk = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category_fk;


    // defining the relationship between the author and post. This is a many to one relationship where the post is the many
    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author_fk;


    // creating the constructor for the post class
    public Post(){}

    public Post(Long postId, String title, String text, Category category_fk, Author author_fk) {
        this.postId = postId;
        this.title = title;
        this.text = text;
        this.category_fk = category_fk;
        this.author_fk = author_fk;
    }

    public Post(String title, String text, Category category_fk, Author author_fk) {
        this.title = title;
        this.text = text;
        this.category_fk = category_fk;
        this.author_fk =  author_fk;
    }

    // here I created the getters and the setters for the post class
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory_fk() {
        return category_fk;
    }

    public void setCategory_fk(Category category_fk) {
        this.category_fk = category_fk;
    }

    public Author getAuthor_fk(){return author_fk;}

    public void setAuthor_fk(Author author_fk){ this.author_fk = author_fk;}



    // converting the post into a string
    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", category_fk=" + category_fk + '\'' +
                ", author_fk=" + author_fk + '\'' +
                '}';
    }


}
