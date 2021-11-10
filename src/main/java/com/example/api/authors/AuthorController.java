package com.example.api.authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/authors", consumes= MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // api get
    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    // api get detailed author
    @GetMapping(path = "{authorId}")
    public Optional<Author> getAnAuthor(@PathVariable("authorId") Long authorId){
        return authorService.getDetailedAuthor(authorId);
    }

    //api post
    @PostMapping
    public Author registerAuthors(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    // api delete
    @DeleteMapping(path = "{authorId}")
    public Map<String, String> deleteAnAuthor(@PathVariable("authorId") Long authorId){
        return authorService.deleteAuthor(authorId);
    }

    // put
    @PutMapping(path = "{authorId}")
    public Author updateAnAuthor(@PathVariable("authorId") Long authorId,
                                @RequestBody Author authors
                              ){
        return authorService.updateAuthor(authorId, authors);

    }
}


