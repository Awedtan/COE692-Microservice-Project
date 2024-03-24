package frontend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ArticlesXML;
import helper.User;

@WebServlet(name = "Login", urlPatterns = { "/login" })
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = API.getUser(username, password);

        if (user != null) {
            showHomepage(request, response, user.getUid());
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    public static void showExplore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArticlesXML articles = API.getPublicArticles();
    }

    public static void showHomepage(HttpServletRequest request, HttpServletResponse response, int uid)
            throws ServletException, IOException {
        ArticlesXML articles = API.getUserArticles(uid);
        User user = API.getUser(uid);
    }
}
