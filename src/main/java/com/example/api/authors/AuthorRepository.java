package com.example.api.authors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// this is to handle the data access, creates an interface for us to communicate with data
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    @Query("SELECT s FROM Author s WHERE s.email = :email")
    Optional<Author> findByEmail(String email);
    Optional<Author> findByAuthorId(Long authorId);
}
