<%-- 
    Document   : home
    Created on : Nov 16, 2017, 9:32:13 AM
    Author     : qmatejka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
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
            if(request.getAttribute("book") != null){
        %>    
                <jsp:include page="/WEB-INF/formBook.jsp" />
        <%}%>

        
    </body>
</html>
