package business;

import persistence.*;
import java.util.ArrayList;
import helper.*;

public class ArticleBusiness {
    public boolean insertArticle(int uid, String title, String content, String token) {
        boolean response = false;
        if (Authenticate.verifyToken(token).getKey()) {
            response = ArticleCRUD.insertArticle(uid, title, content);
        }
        return response;
    }

    public ArticlesXML getPublicArticles() {
        ArrayList<Article> articles = ArticleCRUD.selectPublicArticles();
        ArticlesXML ax = new ArticlesXML();
        ax.setArticles(articles);
        return ax;
    }

    public ArticlesXML getUserArticles(String query) {
        int uid = Integer.parseInt(query);
        ArrayList<Article> articles = ArticleCRUD.selectUserArticles(uid);
        ArticlesXML ax = new ArticlesXML();
        ax.setArticles(articles);
        return ax;
    }
}