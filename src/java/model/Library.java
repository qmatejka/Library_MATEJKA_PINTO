package model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Library {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<User> users = new ArrayList<User>();

    public Library() {
        initLibrary();
    }
    private void initLibrary(){
        Book book1 = new Book("4875412358", "Haruki Murakami", "1Q84", 2, 2);
        Book book2 = new Book("9754132564", "Emile Zola", "Gervaise", 0, 3);
        Book book3 = new Book("4814876133", "Franz Kafka", "Métamorphose", 5, 6);
        this.books.add(book1);
        this.books.add(book2);
        this.books.add(book3);
        
        this.users.add(new User("admin", "admin", true));
        this.users.add(new User("biblio", "biblio", true));
        this.users.add(new User("test", "test", false));
    }
    
    public User getUserByName(String name){
        for(User user:users){
                if(user.getUsername().equals(name)) return user;
        }
        return null;
    }
    
    /**
     * 
     * @param author
     * 	Author of the book 
     * @return
     * 	Book
     * @throws BookNotFoundException
     */
    public Book getBookByAuthor(String author) throws BookNotFoundException{
        for(Book book:books){
                if(book.getAutor().equals(author)) return book;
        }
        throw new BookNotFoundException();
    }
    /**
     * 
     * @param name
     * 	Name of the book
     * @return
     * 	Book from the library
     * @throws BookNotFoundException
     */
    public Book getBookByName(String name) throws BookNotFoundException{
        for(Book book:books){
                if(book.getName().equals(name)) return book;
        }
        throw new BookNotFoundException();
    }
    
    public Book getBookByISBN(String isbn) throws BookNotFoundException{
        for(Book book:books){
                if(book.getIdISBN().equals(isbn)) return book;
        }
        throw new BookNotFoundException();
    }
    
    /**
     * @return the books
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
    /**
     * @param books the books to set
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
