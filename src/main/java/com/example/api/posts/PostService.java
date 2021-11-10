package com.example.api.posts;

import com.example.api.authors.AuthorRepository;
import com.example.api.categories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PostService {

    private final PostRepository postRepository;

    // connecting the repository (data access to the service layer)
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    // get all authors
    public List<Post> getPosts() { return postRepository.findAll();}

    //  add posts
    public Post addPost(Post post) {

        Optional<Post> postOptional = postRepository.findByTitle(post.getTitle());
        if (postOptional.isPresent()){
            throw new IllegalStateException("Title already exists.");
        }

//        Set<Post> postt = new HashSet<>();
//        Author author1 = new Author();
//        Category category1 = new Category();
//
//        Optional<Author> byAuthId = authorRepository.findByAuthorId(post.getAuthor_fk().getAuthorId());
//        if (!byAuthId.isPresent()){
//            throw new ResourceNotFoundException("Author with ID " + post.getAuthor_fk().getAuthorId() + " does not exist.");
//        }
//        Author author = byAuthId.get();
//
//        Optional<Category> byCatId = categoryRepository.findById(post.getCategory_fk().getCategoryId());
//        if (!byCatId.isPresent()){
//            throw new ResourceNotFoundException("Author with ID " + post.getCategory_fk().getCategoryId() + " does not exist.");
//        }
//        Category category = byCatId.get();

        Post post1 = postRepository.save(post);
//        author.setAuthorpost((List<Post>) post1);
//        category.setPostcategory((List<Post>) post1);

        return post1;
    }


    // delete posts
    public Map<String, String> deletePost(Long postId) {
        // check if ID exists
        boolean exists = postRepository.existsById(postId);
        if (!exists) {
            throw new IllegalStateException("Post with ID " + postId + " does not exist.");
        };
        //delete by id
        postRepository.deleteById(postId);
        HashMap<String, String> map = new HashMap<>();
        map.put("Deleted post Id", " " + postId + " ");
        return map;
    }


    @Transactional
    public Post updatePost(Long postId, Post postt) {
        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new IllegalStateException("Post with ID " + postId + " does not exist."));

        // updating fields in the db
        Optional<Post> postOptional = postRepository.findByTitle(post.getTitle());
        if (postOptional.isPresent()){
            if (Objects.equals(postt.getTitle(), postOptional.get().getTitle())) {
                throw new IllegalStateException("Title already exists.");
            }
            post.setTitle(postt.getTitle());
        }
        post.setText(postt.getText());

        return post;
    }

    public Post getDetailedPost(Long postId) {
        return postRepository.findByPostId(postId).orElseThrow(() -> new IllegalStateException("Post with ID " + postId + " does not exist."));
    }

}

