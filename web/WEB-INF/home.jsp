<%-- 
    Document   : home
    Created on : Nov 16, 2017, 9:32:13 AM
    Author     : qmatejka
--%>

<%@page import="model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Library</title>
    </head>
    <body>
        <h1>Online Library</h1>
        <jsp:include page="/WEB-INF/searchBar.jsp" />
        <%
            if(session.getAttribute("user")==null){
        %>
                <jsp:include page="/WEB-INF/login.jsp"/>
        <%
            }else{
                
                out.print("Bonjour "+((User)session.getAttribute("user")).getUsername());
        %>
                <a href="home?action=disconnect">Disconnect</a>
        <%
            }
             User user = (session.getAttribute("user")!=null) ?(User) session.getAttribute("user") : null; 
                Boolean isUser = false;
                Boolean isAdmin = false;
                if(user != null && !user.isAdmin()) isUser=true;
                if(user !=null && user.isAdmin()) isAdmin = true;
                
                if(isAdmin){
             %>
             <h1> Add a new book to the library :  </h1>
            <form name="bookForm" action="home" method="POST">
                ISBN: <input type="text" name="isbn" size="30"/>
                Name: <input type="text" name="name" size="30"/>
                Author: <input type="text" name="author" size="30" />
                Stock total: <input type="number" name="stockTotal" size="30" />
                Stock:  <input type="number" name="stock" size="30" />
               <button type="submit" name="action" value="add">Add</button>

            </form>
             <br />
             
            
            <%    }

            if(request.getAttribute("searchResults") != null){
                ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("searchResults");
                if(!books.isEmpty()){%><h1> Results of your query : </h1> <%}
                

                for(Book book : books){
                  
        %>    
        
                <form name="bookForm" action="home" method="POST">   
                   
                    ISBN: <input type="text" name="isbn" size="30" value="<%=book.getIdISBN()%>" <% if(!book.getIdISBN().equals("")){%>readonly="readonly" style="background:gainsboro"<%}%>/>               
                 
                    
                    Name: <input type="text" name="name" size="30" value="<%=book.getName()%>" />
                    Author: <input type="text" name="author" size="30" value="<%=book.getAuthor()%>"  />
                    Stock total: <input type="number" name="stockTotal" size="30" value="<%=book.getStockTotal()%>" <% if(isUser){%>readonly="readonly" style="background:gainsboro"<%}%>/>
                    Stock:  <input type="number" name="stock" size="30" value="<%=book.getStockAvailable()%>" <% if(isUser){%>readonly="readonly" style="background:gainsboro"<%}%>/>
                    <% if(isAdmin){ %>
                        <button type="submit" name="action" value="update">Update</button>
                        <button type="submit" name="action" value="remove">Remove</button>
                        
                        || Reader: <input type="text" name="reader" size="30" value="" />
                        <button type="submit" name="action" value="bookForUser">Book for the user </button>
                   
                    <% } 
                        if(isUser){
                    %>

                        <button type="submit" name="action" value="Take">Take</button>
                    <%  
                        }
                    %>
                </form>
        <%
                }
            
            }
            if(isUser){
          
            user = (session.getAttribute("user")!=null) ?(User) session.getAttribute("user") : null; 
            if(!user.getBooksTaken().isEmpty()){
             %>
            <h1> Your books to return : </h1>
            <%
                for(Book abook : user.getBooksTaken()){
                       %>    

                       <form name="bookForm" action="home" method="POST">
                           ISBN: <input type="text" name="isbn" size="30" value="<%=abook.getIdISBN()%>" readonly style="background:gainsboro" />
                           Name: <input type="text" name="name" size="30" value="<%=abook.getName()%>" readonly style="background:gainsboro"   />
                           Author: <input type="text" name="author" size="30" value="<%=abook.getAuthor()%>" readonly style="background:gainsboro"   />
                           <button type="submit" name="action" value="Return">Return</button>

                       </form> 
                      <%  
                 }
              
                }   
            }
        %>
        
      
      
            
        
    </body>
</html>
