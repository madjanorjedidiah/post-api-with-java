package com.example.api.authors;

import com.example.api.posts.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;


//   creating the author model
@Data
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
public class Author {
	@Id
	@SequenceGenerator(
			name = "author_sequence",
			sequenceName = "author_sequence",
			allocationSize = 1
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
	private Long authorId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

	// defining the relationship between the author and post. The author is the owner of the relationship
	@JsonIgnore
	@OneToMany(mappedBy = "author_fk", cascade = CascadeType.ALL)
	private Set<Post> authorpost;


	// creating the constructor for the author class
    public Author(){}

    public Author(Long authorId, String firstname, String lastname, String email, String phone, Set<Post> authorpost){
        this.authorId = authorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
		this.authorpost = authorpost;

    }

    public Author(String firstname, String lastname, String email, String phone, Set<Post> authorpost){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
		this.authorpost = authorpost;

    }


	// here I created the getters and the setters for the author class
    public Long getAuthorId() {
		return authorId;
	}
	public void seAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {	this.lastname = lastname;	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public String getPhone() {
		return phone;
	}
	public void setPhone (String phone) {
		this.phone = phone;
    }
	public Set<Post> getAuthorpost() { return authorpost; }
	public void setAuthorpost (Set<Post> authorpost) { this.authorpost = authorpost; }

	// converting the auhtor into a string
	@Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phone=" + phone + ", authorpost=" + authorpost + "}";
    }


}
