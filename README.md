# NE-demo
Demo project for job interview.

## Description
Back-end built with Java, spring-boot, and a H2 database.
Ugly front-end built with Next.js for demo purposes.

## Project constraints
Bookstore
We want you to create a REST api for a small bookstore so that customers may order fresh
books directly from them and so that an admin with the username: “Uncle_Bob_1337” and
password: “TomCruiseIsUnder170cm”, can re-stock books in the bookstore. The system
should be written in java and respond in JSON. The book store sells 4 different books.
The title and pricing is as follows:
book title cost
Book A : Fellowship of the book 5$
Book B : Books and the chamber of books 10$
Book C : The Return of the Book 15$
Book D : Limited Collectors Edition 75$
What the customer desires is to have their books be listed to them in JSON. The JSON
should contain each individual book that they have ordered, and the total price. There are
only 10 copies of Book D and no more can exist in the world.
Limitations
Book A, Book B, and Book C can be restocked in the bookstore in multiples of 10.
Users shouldn’t be able to order more than actually exists in the current stock.
When the stock reaches 0 then the admin needs to order more!
Due to tax reasons the bookstore doesn’t allow purchases above 120$.

