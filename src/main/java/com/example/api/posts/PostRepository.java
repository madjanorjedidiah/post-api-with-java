package com.example.api.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// this is to handle the data access, creates an interface for us to communicate with data
@Repository
//@RepositoryRestResource(excerptProjection = Post.class)
public interface PostRepository extends JpaRepository<Post, Long>  {
    Optional<Post> findByPostId(Long postId);
    Optional<Post> findByTitle(String title);
}
