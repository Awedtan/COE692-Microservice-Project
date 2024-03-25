<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList" import="helper.*"%>

<% String name = (String) request.getAttribute("name");%>
<% ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articles");%>
<% ArrayList<ArrayList<Comment>> comments = (ArrayList<ArrayList<Comment>>) request.getAttribute("comments");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Explore!</title>
    </head>
    <body>
        <h3>Welcome <%= name%>!</h3>
        <form action="frontend" method="post">
            <input type="hidden" name="type" value="home">
            <input type="submit" value="Home">
        </form>
        <center>
            <h1>Explore other articles!</h1>
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
            <br><br><br>
        </center>
    </body>
</html>