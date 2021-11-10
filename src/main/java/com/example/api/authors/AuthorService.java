package com.example.api.authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthorService {

    // calling the repository into the class as an attribute
    private final AuthorRepository authorRepository;

    // connecting the repository (data access to the service layer)
    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    // get all authors
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }


    //  add authors
    public Author addAuthor(Author author) {
        // check if email is in the system already
        String emailRegex = "^.+@.+\\..+$";
        if (!(author.getEmail().matches(emailRegex))){
            throw new IllegalStateException("Email is not standard");
        }

        Optional<Author> authorOptional = authorRepository.findByEmail(author.getEmail());
        if (authorOptional.isPresent()){
            throw new IllegalStateException("Email already exists.");
        }

        // check if phone number is indeed a correct number
        String regexStr = "^[0-9]{10}$";

        if (!(author.getPhone().matches(regexStr))){
            throw new IllegalStateException("Phone number is not standard");
        }

        // save data
        authorRepository.save(author);
        return author;
    }

    // delete author from the db by getting the id first
    public Map<String, String> deleteAuthor(Long authorId) {
        // check if ID exists
        boolean exists = authorRepository.existsById(authorId);
        if (!exists){
            throw new IllegalStateException("Author with ID " + authorId + " does not exist.");
        }
        authorRepository.deleteById(authorId);
        HashMap<String, String> map = new HashMap<>();
        map.put("Deleted author Id", " " + authorId + " ");
        return map;

    }

    // updating the data in the system
    @Transactional
    public Author updateAuthor(Long authorId, Author authors) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalStateException("Author with ID " + authorId + " does not exist."));

        author.setFirstname(authors.getFirstname());
        author.setLastname(authors.getLastname());
        Optional<Author> authorOptional = authorRepository.findByEmail(authors.getEmail());
        try{if (authorOptional.isPresent()){
            if (Objects.equals(author.getFirstname(), authorOptional.get().getFirstname()) && Objects.equals(author.getLastname(), authorOptional.get().getLastname())) {
                author.setEmail(authors.getEmail());
            }}
        } catch (IllegalStateException e) {
            //e.printStackTrace();
            throw new IllegalStateException(("Email already exists."));
        }


        // checking phone number before updating it
        String regexStr = "^[0-9]{10}$";
        if (authors.getPhone().matches(regexStr)){
            author.setPhone(authors.getPhone());
        }
        authorRepository.save(author);
        return author;
    }

    public Optional<Author> getDetailedAuthor(Long authorId) throws ResourceNotFoundException{
        Optional<Author> author = Optional.ofNullable(authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author with ID " + authorId + " does not exist.")));
        return author;
    }
}


//
//{
//    "firstname":"Jed",
//    "lastname":"Madj",
//    "phone":"0244241032",
//    "email":"email@gmail.com"
//}