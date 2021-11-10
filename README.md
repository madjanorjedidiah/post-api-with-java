

# post-api-with-java


##  AUTHOR
- Get all authors (method GET) : **/api/authors
- Get a particular author (method GET) : **/api/authors/{id}
- Create an author (method POST) : **/api/authors
- Update an author (method PUT) : **/api/authors/{id}
- Delete an author (method DELETE) : **/api/authors/{id}




##  POST
- Get all post (method GET) : **/api/posts
- Get a particular post (method GET) : **/api/posts/{id}
- Create an post (method POST) : **/api/posts
- Update an post (method PUT) : **/api/posts/{id}
- Delete an post (method DELETE) : **/api/posts/{id}




##  CATEGORY
- Get all categories (method GET) : **/api/categories
- Get a particular category (method GET) : **/api/categories/{id}
- Create a category (method POST) : **/api/categories
- Update a category (method PUT) : **/api/categories/{id}
- Delete a category (method DELETE) : **/api/categories/{id}
- Get all posts under a category (method GET) : **/api/categories




### Example of CREATE endpoints samples
#### Author
```
{
    "firstname":"Abidts",
    "lastname":"Matde",
    "phone":"0244141011",
    "email":"ematil@geawil.com"
}

```


#### Category
```
{
    "name":"Jedd",
    "description":"Maddj"

}

```

#### Post
```
{
    "title":"EDdy",
    "text":"Cd",
    "author_fk":{
    	"authorId": 1,
	    "firstname":"Abidts",
	    "lastname":"Matde",
	    "phone":"0244141011",
	    "email":"ematil@geawil.com"
		},
	"category_fk":{
		"categoryId": 1,
		"name": "Jedd",
    	"description": "Maddj"
		}
}

```

### Run Project
- Pull the project.
- Navigate into the target folder within your terminal and run:
```
java -jar api-0.0.1-SNAPSHOT.jar

```