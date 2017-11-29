<%-- 
    Document   : newjsp
    Created on : Nov 23, 2017, 10:32:40 AM
    Author     : qmatejka
--%>

<%@page import="model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Book book = (request.getAttribute("book")!=null) ?(Book) request.getAttribute("book") : (Book) new Book("", "", "",0 ); %>    
        <form name="bookForm" action="home" method="POST">
            Name: <input type="text" name="name" size="30" value="<%=book.getName()%>" />
            Author: <input type="text" name="author" size="30" value="<%=book.getAutor()%>"  />
            ISBN: <input type="text" name="isbn" size="30" value="<%=book.getIdISBN()%>" <% if(!book.getIdISBN().equals("")){%>readonly="readonly" style="background:gainsboro"<%}%>/>
            Stock:  <input type="number" name="stock" size="30" value="<%=book.getStockAvailable()%>" />
            
            <button type="submit" name="create" value="create">Create</button>
            <button type="submit" name="update" value="update">Update</button>
        </form>
    </body>
</html>
