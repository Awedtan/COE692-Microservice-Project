package frontend;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.API;
import business.Authenticate;
import helper.Article;
import helper.ArticlesXML;
import helper.Comment;
import helper.User;

@WebServlet(name = "frontend", urlPatterns = { "/frontend" })
public class Frontend extends HttpServlet {

    private static String authCookieName = "auth_token";

    private static Entry<Boolean, Entry<String, String>> readAuthCookie(Cookie[] cookies) {
        Entry<Boolean, String> verify = Authenticate.verifyToken("");
        Entry<String, String> token = new AbstractMap.SimpleEntry<String, String>("", "");
        boolean valid = false;

        for (Cookie c : cookies) {
            if (c.getName().equals(authCookieName)) {
                verify = Authenticate.verifyToken(c.getValue());
                if (verify.getKey()) {
                    token = new AbstractMap.SimpleEntry<String, String>(c.getValue(), verify.getValue());
                    valid = true;
                    break;
                }
            }
        }
        return new AbstractMap.SimpleEntry<Boolean, Entry<String, String>>(valid, token);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("type")) {
            case "login": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = API.getUser(username, password);

                if (user != null) {
                    String token = Authenticate.createToken("login", Integer.toString(user.getUid()));
                    response.addCookie(new Cookie(authCookieName, token));
                    showHomepage(request, response, user);
                } else {
                    showLogin(request, response);
                }
                break;
            }
            case "explore": {
                Entry<Boolean, Entry<String, String>> valid = readAuthCookie(request.getCookies());
                User user = API.getUser(Integer.parseInt(valid.getValue().getValue()));
                if (valid.getKey()) {
                    showExplore(request, response, user);
                } else {
                    showLogin(request, response);
                }
                break;
            }
            case "home": {
                Entry<Boolean, Entry<String, String>> valid = readAuthCookie(request.getCookies());
                User user = API.getUser(Integer.parseInt(valid.getValue().getValue()));
                if (valid.getKey()) {
                    showHomepage(request, response, user);
                } else {
                    showLogin(request, response);
                }
                break;
            }
            case "createarticle": {
                Entry<Boolean, Entry<String, String>> valid = readAuthCookie(request.getCookies());
                if (valid.getKey()) {
                    User user = API.getUser(Integer.parseInt(valid.getValue().getValue()));
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");

                    API.createArticle(user, title, content, valid.getValue().getKey());
                    showHomepage(request, response, user);
                } else {
                    showLogin(request, response);
                }
                break;
            }
        }
    }

    private static void showExplore(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        ArrayList<Article> filtered = new ArrayList<>();
        ArrayList<ArrayList<Comment>> comments = new ArrayList<>();
        for (Article a : API.getPublicArticles().getArticles()) {
            if (a.getAuthorId() == user.getUid())
                continue;
            filtered.add(a);
            comments.add(API.getArticleComments(a.getUid()).getComments());
        }

        request.setAttribute("name", user.getFullName());
        request.setAttribute("articles", filtered);
        request.setAttribute("comments", comments);
        request.getRequestDispatcher("explore.jsp").forward(request, response);
    }

    private static void showHomepage(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        ArticlesXML articles = API.getUserArticles(user.getUid());
        ArrayList<ArrayList<Comment>> comments = new ArrayList<>();
        for (Article a : articles.getArticles()) {
            comments.add(API.getArticleComments(a.getUid()).getComments());
        }

        request.setAttribute("name", user.getFullName());
        request.setAttribute("articles", articles.getArticles());
        request.setAttribute("comments", comments);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    private static void showLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.html").forward(request, response);
    }
}