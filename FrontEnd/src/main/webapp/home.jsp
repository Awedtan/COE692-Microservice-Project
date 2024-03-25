<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList" import="helper.*"%>

<% String name = (String) request.getAttribute("name");%>
<% ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articles");%>
<% ArrayList<ArrayList<Comment>> comments = (ArrayList<ArrayList<Comment>>) request.getAttribute("comments");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Homepage</title>
    </head>
    <body>
        <h3>Welcome <%= name%>!</h3>
        <form action="frontend" method="post">
            <input type="hidden" name="type" value="explore">
            <input type="submit" value="Explore">
        </form>
        <center>
            <h1>Your articles:</h1>
            <% for (int i=0; i<articles.size(); i++) {%>
                <% Article a = articles.get(i);%>
                <h2>
                    <%= a.getTitle()%>
                </h2>
                <h4>
                    Written by User #<%= a.getAuthorId()%>
                </h4>
                <p>
                    <%= a.getContent()%>
                </p>
                <% if(comments.get(i).size() > 0) {%>
                    <h4>
                        Comments
                    </h4>
                    <% for(Comment c : comments.get(i)) {%>
                        <p>
                            <%= c.getContent()%> - User #<%= c.getAuthorId()%>
                        </p>
                    <% }%>
                <% }%>
            <% }%>
            <h1>Write a new article!</h1>    
            <form action="frontend" method="post">
                <input type="hidden" name="type" value="createarticle">
                <input type="text" name="title" value="Title">
                <input type="text" name="content" value="Content">
                <input type="submit" value="Publish">
            </form>
        </center>
    </body>
</html>