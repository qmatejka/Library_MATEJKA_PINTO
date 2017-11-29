package model;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<Book>();

    public Library() {
        initLibrary();
    }
    private void initLibrary(){
        Book book1 = new Book("4875412358", "Haruki Murakami", "1Q84", 2);
        Book book2 = new Book("9754132564", "Emile Zola", "Gervaise", 0);
        Book book3 = new Book("4814876133", "Franz Kafka", "Métamorphose", 5);
        this.books.add(book1);
        this.books.add(book2);
        this.books.add(book3);
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
