/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.BookNotFoundException;
import model.Library;
import model.User;


/**
 *
 * @author qmatejka
 */
public class Controller extends HttpServlet {

    private final static String VIEW_FOLDER = "/WEB-INF";
    private static final long serialVersionUID = -1951358628804251994L;
    private Library library = new Library();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_FOLDER+"/searchBar.jsp");
        Book book = null;
        try {
            book = library.getBookByName("1Q84");
        } catch (BookNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("book", book);
        dispatcher.forward(request, response);
    }
    
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_FOLDER+"/home.jsp");
        if(request.getParameter("action") != null)
            if(request.getParameter("action").equals("disconnect")){
                request.getSession().invalidate();
            }
        String bookName = request.getParameter("name");
        if(bookName != null){
            dispatcher = request.getRequestDispatcher(VIEW_FOLDER+"/home.jsp");
            Book book = null;
            Library library = new Library();
            try {
                book = library.getBookByName(bookName);
            } catch (BookNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("book", book);       
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        Book book = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(request.getParameter("create")!=null){
            book = new Book(request.getParameter("name"), 
                    request.getParameter("author"), 
                    request.getParameter("isbn"), 
                    Integer.parseInt(request.getParameter("stock")), 
                    Integer.parseInt(request.getParameter("stockTotal")));
            request.setAttribute("book", book);       
            rd = request.getRequestDispatcher(VIEW_FOLDER+"/displayBook.jsp");
        }else if(request.getParameter("update") != null){
            try {
                book = library.getBookByISBN(request.getParameter("isbn"));
                book.setName(request.getParameter("name"));
                book.setAutor(request.getParameter("author"));
                book.setStockAvailable(Integer.parseInt(request.getParameter("stock")));
                book.setStockTotal(Integer.parseInt(request.getParameter("stockTotal")));
                request.setAttribute("book", book);  
            } catch (BookNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            rd = request.getRequestDispatcher(VIEW_FOLDER+"/displayBook.jsp");
        }
        else{
            User user = library.getUserByName(username);
            if(user != null){
                if ((user.getUsername().equalsIgnoreCase(username))
                      && (user.getPassword().equals(password))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", username);
                    rd = request.getRequestDispatcher(VIEW_FOLDER+"/home.jsp");
                    //User user = new User(username, password);
                    //request.setAttribute("user", user);
                } else {
                    rd = request.getRequestDispatcher(VIEW_FOLDER+"/error.jsp");
                }
            } else {
                rd = request.getRequestDispatcher(VIEW_FOLDER+"/error.jsp");
            } 
        }
        rd.forward(request, response);
    }
    
    private void testBook(){
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
