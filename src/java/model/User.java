/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author qmatejka
 */
public class User {
    
    private String username;
    private String password;
    private boolean admin;
    private ArrayList<Book> booksTaken;
    
    public User(String username, String password, boolean admin){
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.booksTaken = new ArrayList<Book>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public void addBook(Book book){
        this.booksTaken.add(book);
    }
    
    public void removeBook(Book book){
        for(Book aBook:booksTaken){
           if(aBook.equals(book)) {
               booksTaken.remove(book);
               break;
           }
        }
    }

    public ArrayList<Book> getBooksTaken() {
        return booksTaken;
    }

    public void setBooksTaken(ArrayList<Book> booksTaken) {
        this.booksTaken = booksTaken;
    }
    
    
}
